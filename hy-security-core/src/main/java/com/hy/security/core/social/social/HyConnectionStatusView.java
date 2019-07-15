/**
 * 
 */
package com.hy.security.core.social.social;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhailiang
 *
 */
@Component("connect/status")
public class HyConnectionStatusView extends AbstractView {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//security 默认封装了绑定信息 ，直接所model拿出即可
		Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) model.get("connectionMap");
		//直接判断微信或QQ有没有绑定，有就是true
		Map<String, Boolean> result = new HashMap<>();
		for (String key : connections.keySet()) {
			result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
		}

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}

}
