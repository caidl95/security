package com.hy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hy.dto.User;
import com.hy.dto.UserQueryCondition;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户控制器类
 * jsonView  只允许查看指定的数据
 * @author hy
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@PostMapping("/regist")
	public void regist(User user,HttpServletRequest request) {
		System.out.println("用户注册");
		//不管是注册还是用户绑定用户，都会拿到一个用户唯一标识
		String userId = user.getUsername();
		//将用户id与第三方登录信息存到数据库
		providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
	}
	
	
	@PostMapping
	public User create(@Valid @RequestBody User user ,BindingResult errors) {//valid  配合实体类中的@NotBlank注解使用 校验数据 BindingResult配合valid使用允许方法执行方便记录日志
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
			
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "查询所有用户信息")
	public List<User> query(UserQueryCondition condition,Pageable pageable){
		
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		return users;
	}
	
	@GetMapping("/{id:\\d+}")//正则表达式:\\d+    只允许是数字
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {
		System.out.println("进入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user ,BindingResult errors) {//valid  配合实体类中的@NotBlank注解使用 校验数据 BindingResult配合valid使用允许方法执行方便记录日志
		
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				
				System.out.println(error.getDefaultMessage());
			});
		}
			
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}
	
}
