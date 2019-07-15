package com.hy.security.core.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hy.security.core.properties.quantity.SecurityConstants;

/**
 * 重写UsernamePasswordAuthenticationFilter
 * 改成由手机号码验证登录
 * @author hy
 *
 */
public class SmsCodeAuthenticationFilter extends  AbstractAuthenticationProcessingFilter{
	// ~ Static fields/initializers
	// =====================================================================================

	public static final String HY_FORM_MOBILE_KEY = "mobile";


	private String mobileParameter = HY_FORM_MOBILE_KEY;
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================
	//手机号码登录提交请求地址
	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
	}

	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"验证方法不支持当前的: " + request.getMethod());
		}

		String mobile = obtainMobile(request);
		

		if (mobile == null) {
			mobile = "";
		}


		mobile = mobile.trim();

		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 
	 */
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	/**
	*
	 */
	protected void setDetails(HttpServletRequest request,
			SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

}
