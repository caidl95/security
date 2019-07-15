package com.hy.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.hy.security.core.properties.browser.BrowserProperties;
import com.hy.security.core.properties.code.ValidateCodeProperties;
import com.hy.security.core.properties.social.SocialProperties;

/**
 * 配置文件读取类
 * ConfigurationProperties(prefix = "hy.security")表示这个类会读取所有以hy.security开头的配置项
 * @author hy
 *
 */
@ConfigurationProperties(prefix = "hy.security")
public class SecurityProperties {
	
	private BrowserProperties browser = new BrowserProperties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();
	
	private SocialProperties social = new SocialProperties();
	
	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public SocialProperties getSocial() {
		return social;
	}

	public void setSocial(SocialProperties social) {
		this.social = social;
	}
	
	
}
