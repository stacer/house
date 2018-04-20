package com.mooc.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mooc.house.mapper.service.IUserService;
import com.mooc.house.model.User;

/**
 * 步骤第三章1.5.4 测试freemarker
 * @author 326873
 *
 */
@Controller
public class HelloController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("hello")
	public String hello(ModelMap map) {
		List<User> users = userService.getAllUsers();
		User user = users.get(0);
		map.addAttribute("user", user);
		return "hello";
	}
	
	/**
	 * 步骤第三章1.6.3 测试freemarker
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/homepage/index";
	}
}
