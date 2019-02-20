package com.imooc.miaosha.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private Integer id;
	private String name;

}
