package com.hy.security.core.properties.browser;

import com.hy.security.core.properties.quantity.LoginType;
import com.hy.security.core.properties.quantity.SecurityConstants;
import com.hy.security.core.properties.session.SessionProperties;

/**
 * 浏览器的内容实体类
 * @author hy
 *
 */
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_URL;
	
	/**
	 * 默认使用准备登录页面，如果用户在配置上配置了自己的页面就使用用户的界面
	 */
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	/**
	 * 默认使用JSON格式
	 */
	private LoginType loginType = LoginType.JSON;
	
	/**
	 * 默认配置为1小时，单位为秒
	 */
	private int rememberMeSeconds = 3600;
	
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	
	
}
