package com.hy.security.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.social.connect.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.hy.security.browser.entity.SimpleResponse;
import com.hy.security.browser.entity.SocialUserInfo;
import com.hy.security.core.properties.SecurityProperties;
import com.hy.security.core.properties.quantity.SecurityConstants;

/**
 * 身份验证控制器类
 * @author hy
 *
 */
@RestController
public class BrowserSercurityController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());//日志
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	/**
	 * 当需要身份认证时跳转到这里
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest != null) {//判断是否为空请求
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是："+targetUrl);
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {//判断是否是html发起的请求，不是就不做处理
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			}
		}
				
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
	}
	
	@GetMapping("/soci/user")
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		return userInfo;
		
	}
}
