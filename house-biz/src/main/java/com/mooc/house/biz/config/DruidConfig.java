package com.mooc.house.biz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;

/**
 * 步骤:第三章1.4.1.2 创建DruidConfig
 * @author Administrator
 *
 */
@Configuration
public class DruidConfig {
	
	/**
	 * 配置DruidDataSource
	 * @param statFilter
	 * @return
	 */
	//步骤:第三章1.4.1.2.4 与配置文件进行属性绑定
	@ConfigurationProperties(prefix="spring.druid") 
	@Bean //第三章1.4.1.2.1配置DruidDatasource
	public DruidDataSource dataSource(Filter statFilter) {
		DruidDataSource dataSource = new DruidDataSource();
		//将statFilter设置到datasource中,这一步先引入guava
		dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
		return dataSource;
	}
	
	/**
	 * 步骤:第三章1.4.1.2.2 
	 * 配置StatFilter
	 * @return
	 */
	@Bean
	public Filter statFilter() {
		StatFilter filter = new StatFilter();
		//多长时间标识为慢sql
		filter.setSlowSqlMillis(5000);
		//是否打印慢sql
		filter.setLogSlowSql(true);
		//是否合并慢sql
		filter.setMergeSql(true);
		return filter;
	}
	
	/**
	 * 步骤:第三章1.4.1.2.5
	 * 添加监控并设置拦截url
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
	}
}
