package com.hy.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常
 * @author hy
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class)//所有需要处理的异常
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//异常响应的状态码
	public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
		Map<String,Object> result = new HashMap();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}
}
