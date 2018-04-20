package com.mooc.house.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 步骤：第三章 1.12.2
 * 创建自定义异常处理器
 * @author 326873
 *
 */
@ControllerAdvice
public class ErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
	
	@ExceptionHandler(value= {Exception.class,RuntimeException.class})
	public String error500(HttpServletRequest request,Exception e) {
		logger.error(e.getMessage(),e);
		logger.error(request.getRequestURL() + "encounter 500");
		return "error/500";
	}
}
