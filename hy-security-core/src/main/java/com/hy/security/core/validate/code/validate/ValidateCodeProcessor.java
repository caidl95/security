package com.hy.security.core.validate.code.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同效验的处理逻辑
 * @author hy
 *
 */
public interface ValidateCodeProcessor {
	
	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	

	
	/**
	 * 创建校验码
	 */
	void create(ServletWebRequest request) throws Exception;
	
	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);
}
