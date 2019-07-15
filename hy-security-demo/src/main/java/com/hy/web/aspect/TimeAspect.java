package com.hy.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 切片类
 * @author hy
 *
 */
//@Aspect
//@Component
public class TimeAspect {
	
	/**
	 * 切入点
	 * UserController  全包含
	 * 被调用的时候   
	 * @throws Throwable 
	 */
	@Around("execution(* com.hy.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("time aspect start");
		
		Object [] args = pjp.getArgs();
		for(Object arg:args) {
			System.out.println("arg is "+arg);
		}
		
		long start = new Date().getTime();
		
		Object object = pjp.proceed();
		
		System.out.println("time aspect 耗时:" + (new Date().getTime() - start));
		
		System.out.println("time aspect end");
		return object;
	}
	
}
