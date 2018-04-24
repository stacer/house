package com.mooc.house.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mooc.house.Utils.UserHelper;
import com.mooc.house.biz.service.IUserService;
import com.mooc.house.common.model.User;
import com.mooc.house.common.result.ResultMsg;


@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/users")
	public List<User> getUsers() {
		return this.userService.getAllUsers();
	}
	
	/**
	 * 步骤:第四章1.2 注册对应的方法
	 * 此方法包含注册逻辑:1.注册验证 2.发送邮件 3.验证失败重定向到注册页
	 * 也包含了跳转到注册页的功能
	 * 通过判断account对象是否为空
	 * @param account
	 * @param modelMap
	 * @return
	 */
	public String accountRegister(User account,ModelMap modelMap) {
		if(account == null || account.getName() == null) {
			return "/user/accounts/register";
		}
		//用户验证
		ResultMsg resultMsg = UserHelper.validate(account);
		if(resultMsg.isSuccess() && userService.addAccount(account)) {
			return "/user/accounts/registerSubmit";
		}else {
			return "redirect:/user/accounts/register?" + resultMsg.asUrlParams();
		}
		
	}
	
}
