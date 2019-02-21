package com.imooc.miaosha.dao;

import com.imooc.miaosha.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User,Integer> {

	
}
