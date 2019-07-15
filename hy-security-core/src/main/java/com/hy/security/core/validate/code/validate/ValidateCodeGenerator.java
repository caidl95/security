package com.hy.security.core.validate.code.validate;

import org.springframework.web.context.request.ServletWebRequest;
import com.hy.security.core.validate.code.entity.ValidateCode;

/**
 * 校验图片的生成器接口
 * @author hy
 *
 */
public interface ValidateCodeGenerator {
	
	ValidateCode generate(ServletWebRequest request);
}
