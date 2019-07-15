/**
 * 
 */
package com.hy.security.core.social.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.hy.security.core.social.api.QQ;
import com.hy.security.core.social.entity.QQUserInfo;

/**
 * @author hy
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		return true;//用来测试当前api是否可用的 
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
			QQUserInfo  userInfo = api.getUserInfo();
			values.setDisplayName(userInfo.getNickname());
			values.setImageUrl(userInfo.getFigureurl_1());
			values.setProfileUrl(null);//设置个人主页，如微博主页
			values.setProviderUserId(userInfo.getOpenId());	
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// do noting
		
	}

}
