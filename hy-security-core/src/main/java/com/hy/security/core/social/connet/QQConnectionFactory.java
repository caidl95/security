package com.hy.security.core.social.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.hy.security.core.social.api.QQ;

/**
 * 
 * @author hy
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String appId,String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	
	}

}
