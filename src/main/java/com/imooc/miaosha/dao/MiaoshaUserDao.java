package com.imooc.miaosha.dao;

import com.imooc.miaosha.domain.User;

import com.imooc.miaosha.domain.MiaoshaUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiaoshaUserDao  extends CrudRepository<MiaoshaUser,Long> {

}
