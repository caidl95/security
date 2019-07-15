package com.hy.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.hy.validator.MyConstraint;

/**
 * 用户的实体类
 * jsonView  使用接口来声明视图
 * @author hy
 *
 */
public class User {
	
	/**
	 * 简单视图
	 * @author hy
	 */
	public interface UserSimpleView {};
	
	/**
	 * 详细视图  并继承简单视图 
	 * @author hy
	 */
	public interface UserDetailView extends UserSimpleView {};
	
	private String id;
	
	@MyConstraint(message = "这是一个测试")
	private String username;
	
	/**
	 * 不允许为空
	 */
	@NotBlank(message = "密码不能为空")
	private String password;
	
	/**
	 * 必须是过去的时间
	 */
	@Past(message = "生日必须是过去的时间")
	private Date birthday;
	
	/**
	 * 指定可以显示的视图
	 * @return
	 */
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 指定可以显示的视图
	 * @return
	 */
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 指定可以显示的视图  特定显示
	 * @return
	 */
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	/**
	 * 一直显示
	 * @return
	 */
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
