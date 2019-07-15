/**
 * 
 */
package com.hy.security.core.properties.social;

/**
 * QQ配置类
 * @author hy
 *
 */
public class SocialProperties {
	/**
	 * 
	 */
	private String filterProcessesIrl = "/auth";
	
	/**
	 * 
	 */
	private QQProperties qq= new QQProperties();
	
	/**
	 * 
	 */
	private WeixinProperties weixin = new WeixinProperties();
	
	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}

	public String getFilterProcessesIrl() {
		return filterProcessesIrl;
	}

	public void setFilterProcessesIrl(String filterProcessesIrl) {
		this.filterProcessesIrl = filterProcessesIrl;
	}

	public WeixinProperties getWeixin() {
		return weixin;
	}

	public void setWeixin(WeixinProperties weixin) {
		this.weixin = weixin;
	}
	
	
}
