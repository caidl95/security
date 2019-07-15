package com.hy.web.service.impl;

import org.springframework.stereotype.Service;

import com.hy.web.service.HelloService;
/**
 * 测试业务层实现类
 * @author hy
 *
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("Hello servic");
		return "Hello" + name;
	}

}
