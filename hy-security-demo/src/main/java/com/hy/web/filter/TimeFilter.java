package com.hy.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;

/**
 * 过滤器
 * @author hy
 *
 */
//@Component
public class TimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("time filter init");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		Long start = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("time filter finsh 耗时：" + (new Date().getTime() - start));

	}

	@Override
	public void destroy() {
		System.out.println("time filter destroy");

	}

}
