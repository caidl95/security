package com.hy.security.core.social.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.hy.security.core.properties.SecurityProperties;
import com.hy.security.core.properties.social.QQProperties;
import com.hy.security.core.social.connet.QQConnectionFactory;

/**
 * 
 * @author hy
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "hy.security.social.qq", name = "app-id")//需要在demo内配置才执行以下代码
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
