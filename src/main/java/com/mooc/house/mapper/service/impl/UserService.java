package com.mooc.house.mapper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooc.house.mapper.UserMapper;
import com.mooc.house.mapper.service.IUserService;
import com.mooc.house.model.User;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}

}
