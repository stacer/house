package com.mooc.house.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 步骤:第三章1.2.3
 * 自定义过滤器的配置
 * @author 326873
 *
 */
@Configuration
public class FiterBeanConfig {
	
	/**
	 * 1.构造filter
	 * 2.配置拦截urlPattern
	 * 3.利用FilterRegistrationBean进行包装
	 * @return
	 */
	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new LogFilter());
		List<String> urlLsit = new ArrayList<String>();
		urlLsit.add("*");
		filterRegistrationBean.setUrlPatterns(urlLsit);
		return filterRegistrationBean;
	}
	
}
