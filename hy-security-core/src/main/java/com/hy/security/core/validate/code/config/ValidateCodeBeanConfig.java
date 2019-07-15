package com.hy.security.core.validate.code.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hy.security.core.properties.SecurityProperties;
import com.hy.security.core.validate.code.validate.SmsCodeSender;
import com.hy.security.core.validate.code.validate.ValidateCodeGenerator;
import com.hy.security.core.validate.code.validate.impl.DefaultSmsCodeSender;
import com.hy.security.core.validate.code.validate.impl.ImageValidateCodeGenerator;

/**
 * 图形验证码的配置类
 * @author hy
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name ="imageValidateCodeGenerator")//当spring启动时会去容器找name中的baen如果已有则不会执行以下
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageValidateCodeGenerator codeGenerator = new ImageValidateCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)//当spring启动时会去容器找此接口的实现类如有则不会执行以下方法
	public SmsCodeSender smsCodeSender() {
		//TODO
		return new DefaultSmsCodeSender();
	}
}
