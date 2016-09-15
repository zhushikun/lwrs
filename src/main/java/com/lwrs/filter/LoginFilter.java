package com.lwrs.filter;


import com.lwrs.utils.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zsk on 16/9/15.
 */
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Logger.info(this, "LoginFilter checked");

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}
}
