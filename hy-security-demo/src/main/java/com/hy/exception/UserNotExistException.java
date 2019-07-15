package com.hy.exception;

/**
 * 用户不存在异常类
 * @author hy
 *
 */
public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = -8375188778077224114L;
	
	private String id;
	
	public UserNotExistException() {
		super();
	}
	
	public UserNotExistException(String id) {
		super("user not exist");
		this.id=id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
