/**
 * 
 */
package com.hy.security.browser.entity;

/**
 * 简单的响应实体类
 * @author hy
 *
 */
public class SimpleResponse {

	private Object content;
	
	public SimpleResponse() {
		super();
	}
	
	public SimpleResponse(Object content) {
		this.content=content;
	}
	
	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	
}
