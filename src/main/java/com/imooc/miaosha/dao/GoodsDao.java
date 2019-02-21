package com.imooc.miaosha.dao;

import java.util.List;

import com.imooc.miaosha.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.miaosha.domain.MiaoshaGoods;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao extends CrudRepository<Goods,Long> {
  @Query(value = "select " +
					"g.id as id, " +
					"g.goods_name as goodsName, " +
					"g.goods_img as goodsImg, " +
					"g.goods_price as goodsPrice, " +
					"mg.stock_count as stockCount, " +
					"mg.start_date as startDate, " +
					"mg.end_date as endDate," +
					"mg.miaosha_price as miaoshaPrice " +
					"from miaosha_goods mg left join goods g " +
					"on mg.goods_id = g.id",nativeQuery = true)
	public List<GoodsVo> listGoodsVo();

	@Query(value = "select " +
					"g.id as id, " +
					"g.goods_name as goodsName, " +
					"g.goods_img as goodsImg, " +
					"g.goods_price as goodsPrice, " +
					"mg.stock_count as stockCount, " +
					"mg.start_date as startDate, " +
					"mg.end_date as endDate," +
					"mg.miaosha_price as miaoshaPrice " +
					"from miaosha_goods mg left join goods g " +
					"on mg.goods_id = g.id where g.id = :goodsId",nativeQuery = true)
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

	@Modifying
	@Query(value = "update miaosha_goods set stock_count = stock_count - 1 where goods_id = :goodsId", nativeQuery = true)
	public int reduceStock(@Param("goodsId") long goodsId);

}
