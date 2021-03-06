package com.hy.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 多线程的测试类
 * @author hy
 *
 */
@RestController
public class AsynController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder; 
	
	@RequestMapping("/deferred")
	public DeferredResult<String> deferred() throws Exception {
		logger.info("主线程开始");
		
		String orderNumber = RandomStringUtils.randomNumeric(8);
		
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);
		
		
		logger.info("主线程返回");
		return result;
	}
	
	
	//同步处理
	@RequestMapping("/order")
	public String order() throws Exception {
		logger.info("主线程开始");
		Thread.sleep(1000);
		logger.info("主线程返回");
		return "success";
	}
	
	//异步处理
	@RequestMapping("/callable")
	public Callable<String> callable() throws Exception {
		logger.info("主线程开始");
		Callable<String> result = new Callable<String>() {
			@Override
			public String call() throws Exception {
				logger.info("副线程开始");
				Thread.sleep(1000);
				logger.info("副线程返回");
				return "success";
			}
		};
		logger.info("主线程返回");
		return result;
	}
}
