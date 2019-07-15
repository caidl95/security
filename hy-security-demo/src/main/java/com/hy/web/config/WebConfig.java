package com.hy.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hy.web.interceptor.TimeInterceptor;

/**
 * 配置类
 * @author hy
 *
 */
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeInterceptor timeInterceptor;
	 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	
	
	
	/*	
	 *添加过滤器的bean节点
	 * @Bean public FilterRegistrationBean timeFilter() { FilterRegistrationBean
	 * registrationBean = new FilterRegistrationBean();
	 * 
	 * TimeFilter timeFilter = new TimeFilter();
	 * registrationBean.setFilter(timeFilter);
	 * 
	 * List<String> urls = new ArrayList(); urls.add("/*");//路径配置/*表示通配
	 * registrationBean.setUrlPatterns(urls);
	 * 
	 * return registrationBean; }
	 */
	
}
