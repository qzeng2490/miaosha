package com.imooc.miaosha.vo;

import java.util.Date;

import com.imooc.miaosha.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;


public interface GoodsVo{
//	private Long id;
//	private String goodsName;
//	private String goodsTitle;
//	private String goodsImg;
//	private String goodsDetail;
//	private Double goodsPrice;
//	private Integer goodsStock;
//	private Double miaoshaPrice;
//	private Integer stockCount;
//	private Date startDate;
//	private Date endDate;

	Long getId();
	String getGoodsName();
//	String getGoodsTitle();
//	String getGoodsImg();
//	String getGoodsDetail();
//	Double getGoodsPrice();
//	Integer getGoodsStock();
	Double getMiaoshaPrice();
	Integer getStockCount();
	Date getStartDate();
	Date getEndDate();
}
