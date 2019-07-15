package com.hy.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 延迟结果的固定器类
 * @author hy
 *
 */
@Component
public class DeferredResultHolder {
	
	private Map<String,DeferredResult<String>> map = new HashMap<>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}
	
	
	
}
