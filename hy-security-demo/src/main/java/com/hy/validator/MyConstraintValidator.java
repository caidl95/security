package com.hy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hy.web.service.HelloService;
/**
 * 这是一个 接口，具体的约束验证类需要实现该接口
 * @author hy
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	
	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		
		return true;
	}

}
