package com.mooc.house.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooc.house.biz.service.IUserService;
import com.mooc.house.common.model.User;


@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/users")
	public List<User> getUsers() {
		return this.userService.getAllUsers();
	}
	
}
