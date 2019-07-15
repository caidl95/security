package com.hy.security.core.validate.code.validate;

/**
 * 短信发送器的接口类
 * @author hy
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile,String code);
}
