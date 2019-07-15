package com.hy.security.browser.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.hy.security.core.authentication.AbstractChannelSecurityConfig;
import com.hy.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.hy.security.core.properties.SecurityProperties;
import com.hy.security.core.properties.quantity.SecurityConstants;
import com.hy.security.core.validate.code.config.ValidateCodeSecurityConfig;

/**
 * 网页安全登录认证配置类
 * @author hy
 *
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private DataSource dataSource; 
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer hySocialSecurityConfig;
	
	//@Autowired
	//private InvalidSessionStrategy invalidSessionStrategy;
	
	//@Autowired
	//private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	/**
	 * 添加bean 使passwordEncoder生效  spring会自行调用对密码，进行比对
	 * @return
	 */
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	/**
	 * 添加bean  记住我功能的配置
	 * @return
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		//tokenRepository.setCreateTableOnStartup(true);//系统启动时默认创建表，如果已经有表则会报错
		return tokenRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		applyPasswordAuthenticationConfig(http);
		/**
		 *http.httpVasic()  默认使用
		 * 登录配置
		 */
		http.apply(validateCodeSecurityConfig)//添加自定义过滤器在用户密码执行前面
			.and()
			.apply(hySocialSecurityConfig)
			.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())//从可配置类读出
				.userDetailsService(userDetailsService)
			.and()
//			.sessionManagement()
//			.invalidSessionStrategy(invalidSessionStrategy)
//			.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
//			.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
//			.expiredSessionStrategy(sessionInformationExpiredStrategy)
//			.and()
//			.and()
			.authorizeRequests()//对请求做个授权
			.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
					SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
					securityProperties.getBrowser().getLoginPage(),//可以在demo内配置登录页面
					"/index.css",
					"/1555552102.png",
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
					securityProperties.getBrowser().getSignUpUrl(),//可以在demo内配置注册页面
					"/user/regist"
					).permitAll()//对此页面放行  不对身份做验证
			.anyRequest()//任何请求
			.authenticated()//都需要身份认证
			.and()
			.csrf().disable()//关掉跨站请求的防护  token
			.apply(smsCodeAuthenticationSecurityConfig);//添加配置
	}

	
}
