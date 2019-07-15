/**
 * 
 */
package com.hy.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hy.security.core.properties.quantity.SecurityConstants;


/**
 * 网页安全登录认证通用配置类
 * @author zhailiang
 *
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler hyAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler hyAuthenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()//使用表单登录
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)//自定义登录页面
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//告诉spring验证的路径spring会调用相关方法
			.successHandler(hyAuthenticationSuccessHandler)//指定登录成功处理器用自己写的
			.failureHandler(hyAuthenticationFailureHandler);//指定登录失败处理器用自己写的
	}
	
}
