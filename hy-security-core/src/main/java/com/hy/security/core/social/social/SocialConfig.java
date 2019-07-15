package com.hy.security.core.social.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import com.hy.security.core.properties.SecurityProperties;

/**
 * 配置社交登录后关联用户信息时会自行写入数据库
 * @author hy
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired(required = false)//在demo项目里面有可能不提供
	private ConnectionSignUp connectionSignUp; 

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());//Encryptors.noOpText()用于信息加密
		//repository.setTablePrefix("hy_");//可以设置表的前缀;数据库的表
		if(connectionSignUp !=null){
			repository.setConnectionSignUp(connectionSignUp);
		}
		
		return repository; 
	}
	
	@Bean
	public SpringSocialConfigurer hySocialSecurityConfig() {
		String filterProcessesIrl = securityProperties.getSocial().getFilterProcessesIrl();						
		HySpringSocialConfigurer configurer = new HySpringSocialConfigurer(filterProcessesIrl);
		configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		return configurer;
	}
	
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
	}
}
