package com.hy.security.core.properties.social;

import org.springframework.boot.autoconfigure.social.SocialProperties;
/**
 * 
 * @author hy
 *
 */
public class QQProperties extends SocialProperties {
	
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
	
}
