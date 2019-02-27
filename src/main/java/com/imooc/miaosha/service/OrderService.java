//package com.imooc.miaosha.service;
//
//import java.util.Date;
//
//import com.imooc.miaosha.dao.OrderInfoDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.imooc.miaosha.dao.MiaoshaOrderDao;
//import com.imooc.miaosha.domain.MiaoshaOrder;
//import com.imooc.miaosha.domain.MiaoshaUser;
//import com.imooc.miaosha.domain.OrderInfo;
//import com.imooc.miaosha.vo.GoodsVo;
//
//@Service
//public class OrderService {
//
//	@Autowired
//	MiaoshaOrderDao miaoshaOrderDao;
//
//	@Autowired
//	OrderInfoDao orderInfoDao;
//
//	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
//		return miaoshaOrderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
//	}
//
//	@Transactional
//	public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
//		OrderInfo orderInfo = new OrderInfo();
//		orderInfo.setCreateDate(new Date());
//		orderInfo.setDeliveryAddrId(0L);
//		orderInfo.setGoodsCount(1);
//		orderInfo.setGoodsId(goods.getId());
//		orderInfo.setGoodsName(goods.getGoodsName());
//		orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
//		orderInfo.setOrderChannel(1);
//		orderInfo.setStatus(0);
//		orderInfo.setUserId(user.getId());
//		orderInfo = orderInfoDao.save(orderInfo);
//		MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
//		miaoshaOrder.setGoodsId(goods.getId());
//		miaoshaOrder.setOrderId(orderInfo.getId());
//		miaoshaOrder.setUserId(user.getId());
//		miaoshaOrderDao.save(miaoshaOrder);
//		return orderInfo;
//	}
//
//}

package com.imooc.miaosha.service;

import java.util.Date;

import com.imooc.miaosha.dao.MiaoshaOrderDao;
import com.imooc.miaosha.dao.OrderDao;
import com.imooc.miaosha.redis.OrderKey;
import com.imooc.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.miaosha.domain.MiaoshaOrder;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.vo.GoodsVo;

@Service
public class OrderService {

	private final OrderDao orderDao;

	private final MiaoshaOrderDao miaoshaOrderDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RedisService redisService;

	@Autowired
	public OrderService(OrderDao orderDao,
											MiaoshaOrderDao miaoshaOrderDao,
											RedisService redisService) {
		this.orderDao = orderDao;
		this.miaoshaOrderDao = miaoshaOrderDao;
		this.redisService = redisService;
	}

	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
//		return miaoshaOrderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
		return redisService.get(OrderKey.getMiaoshaOrderByUidGid, ""+userId+"_"+goodsId, MiaoshaOrder.class);
	}

	public OrderInfo getOrderById(long orderId) {
		return orderDao.getOrderById(orderId);
	}


	@Transactional
	public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		orderDao.insert(orderInfo);
		MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
		miaoshaOrder.setGoodsId(goods.getId());
		miaoshaOrder.setOrderId(orderInfo.getId());
		miaoshaOrder.setUserId(user.getId());
		miaoshaOrderDao.insertMiaoshaOrder(miaoshaOrder);

//		logger.error("---------------"+miaoshaOrder.getId());

		redisService.set(OrderKey.getMiaoshaOrderByUidGid, ""+user.getId()+"_"+goods.getId(), miaoshaOrder);

		return orderInfo;
	}

	public void deleteOrders() {
		orderDao.deleteOrders();
		orderDao.deleteMiaoshaOrders();
	}

}

