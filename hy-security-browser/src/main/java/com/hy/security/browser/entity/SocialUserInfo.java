package com.hy.security.browser.entity;

/**
 * 第三方用户信息
 * @author hy
 *
 */
public class SocialUserInfo {
	/**
	 * 第三方用户id
	 */
	private String providerId;
	
	/**
	 * 第三方用户openId
	 */
	private String providerUserId;
	
	/**
	 * 用户的昵称
	 */
	private String nickname;
	/**
	 * 用户的头像
	 */
	private String headimg;
	
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getProviderUserId() {
		return providerUserId;
	}
	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
}
