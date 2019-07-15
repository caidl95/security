package com.hy.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.hy.security.core.validate.code.entity.ImageCode;
import com.hy.security.core.validate.code.validate.ValidateCodeGenerator;

/**
 * 可以实现更高的图形验证码重写
 * @author hy
 *
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode generate(ServletWebRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
