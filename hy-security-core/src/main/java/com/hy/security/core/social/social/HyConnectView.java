package com.hy.security.core.social.social;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 *  配置绑定微信或解绑微信 "connect/weixinConnect", "connect/weixinConnected"
 * @author zhailiang
 *
 */
public class HyConnectView extends AbstractView {

	/**
	 * 判断是否成功绑定或解绑
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		if (model.get("connection") == null) {
			response.getWriter().write("<h3>解绑成功</h3>");
		} else {
			response.getWriter().write("<h3>绑定成功</h3>");
		}

	}

}
