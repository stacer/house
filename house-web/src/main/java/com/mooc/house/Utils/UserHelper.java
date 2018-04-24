package com.mooc.house.Utils;

import org.apache.commons.lang3.StringUtils;

import com.mooc.house.common.model.User;
import com.mooc.house.common.result.ResultMsg;

public class UserHelper {
	
	public static ResultMsg validate(User account) {
		if(StringUtils.isBlank(account.getEmail())) {
			return ResultMsg.errorMsg("Email有误");
		}
		if(StringUtils.isBlank(account.getName())) {
			return ResultMsg.errorMsg("名字有误");
		}
		if(StringUtils.isBlank(account.getConfirmPasswd()) || StringUtils.isBlank(account.getPasswd()) 
				|| !account.getPasswd().equals(account.getConfirmPasswd())) {
			return ResultMsg.errorMsg("密码 有误");
		}
		if(account.getPasswd().length() <6) {
			return ResultMsg.errorMsg("密码要大于6位");
		}
		return ResultMsg.successMsg("");
	}
	
}
