package com.hy.security.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 获取用户信息的组建
 * 需要在现在类中实现
 * @author hy
 *
 */
//@Component
public class MyUserDetailsService implements UserDetailsService ,SocialUserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;//用于密码加密
	
	/**
	 * 登录时使用
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(username);
		return buildUser(username);
	}
	
	/**
	 * 社交授权登录
	 */
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info(userId);
		return buildUser(userId);
	}
	
	private SocialUserDetails buildUser(String userId) {
		
		//根据用户名查询用户信息
		String password = "123456";//从数据库活得信息
		//passwordEncoder.encode(password);//加密需要在注册用户时进行，此处用来测试
		//根据查询到的用户信息判断用户是否被冻结
		//TODO
		// 验证用户名跟密码，权限
	//	return new User(username,password,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return new SocialUser(userId,password,
				true,//判断账号是否过期了
				true,//判断账号是否被冻结了
				true,//判断用户密码是否过去了
				true,//判断用户是否被删除
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));//确认权限，此处应由注册时分配
	}
}
