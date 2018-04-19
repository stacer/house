package com.mooc.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooc.house.mapper.service.IUserService;
import com.mooc.house.model.User;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/users")
	public List<User> getUsers() {
		return this.userService.getAllUsers();
	}
	
}
