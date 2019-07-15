/**
 * 
 */
package com.hy.security.core.validate.code.ex;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证异常类
 * @author hy
 *
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
