package com.imooc.miaosha.service;

import java.util.List;

import com.imooc.miaosha.dao.MiaoshaGoodsDao;
import com.imooc.miaosha.domain.MiaoshaGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.miaosha.dao.GoodsDao;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService {
	
	@Autowired
	GoodsDao goodsDao;

	@Autowired
	MiaoshaGoodsDao miaoshaGoodsDao;
	
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.listGoodsVo();
	}

	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	public void reduceStock(GoodsVo goods) {
		goodsDao.reduceStock(goods.getId());
	}

	public void resetStock(List<GoodsVo> goodsList) {
		for(GoodsVo goods : goodsList ) {
			MiaoshaGoods g = new MiaoshaGoods();
			g.setGoodsId(goods.getId());
			g.setStockCount(10);
			miaoshaGoodsDao.resetStock(g);
		}
	}
	
	
	
}
