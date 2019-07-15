package com.hy.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Target 声明是否能在哪里加注解
 * @author hy
 *
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	
	String message() default "{javax.validation.constraints.Past.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}	
