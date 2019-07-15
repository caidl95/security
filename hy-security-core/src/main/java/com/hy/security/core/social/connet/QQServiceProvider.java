package com.hy.security.core.social.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.hy.security.core.social.api.QQ;
import com.hy.security.core.social.api.impl.QQImpl;

/**
 * 服务提供商
 * @author hy
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
	
	private String appId;
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId,String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken,appId);
	}

}
