package com.hy.web.async;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 模拟队列
 * @author hy
 *
 */
@Component
public class MockQueue {
	
	private String placeOrder;
	
	private String completeOrder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) throws InterruptedException {
		new Thread(() -> {	
			try {
				logger.info("接到下单请求，"+placeOrder);				Thread.sleep(1000);
				this.completeOrder = placeOrder;
				logger.info("下单请求处理完毕，"+placeOrder);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
		}).start();
		
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
	
	
}
