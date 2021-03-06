package com.mooc.house.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooc.house.mapper.UserMapper;
import com.mooc.house.model.User;
import com.mooc.house.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}

}
