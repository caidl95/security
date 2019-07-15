/**
 * 
 */
package com.hy.web.connection;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 第三方用户访问该服务器时默认注册此用户
 * @author zhailiang
 *
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ConnectionSignUp#execute(org.springframework.social.connect.Connection)
	 */
	@Override
	public String execute(Connection<?> connection) {
		//根据社交用户信息默认创建用户并返回用户唯一标识
		return connection.getDisplayName();
	}

}
