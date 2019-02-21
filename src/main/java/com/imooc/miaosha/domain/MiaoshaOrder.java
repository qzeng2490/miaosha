package com.imooc.miaosha.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoshaOrder {
	@Id
	@GeneratedValue
	private Long id;
	private Long userId;
	private Long  orderId;
	private Long goodsId;
}
