package com.imooc.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domain.User;

import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public Optional<User> getById(int id) {
		 return userDao.findById(id);
	}

	@Transactional
	public boolean tx() {
		User u1= new User(2,"2222");
		userDao.save(u1);
		
		User u2= new User(1,"11111");
		userDao.save(u2);
		
		return true;
	}
	
}
