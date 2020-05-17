package org.springframework.webflow.filter;



import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;



@Slf4j
public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		log.debug("init filter {} ", getClass().getName());
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String url = request instanceof HttpServletRequest ? ((HttpServletRequest) request)
				.getRequestURL()
				.toString() : "N/A";
		log.debug("\n\nfrom filter, processing url: {}\n\n", url);
		chain.doFilter(request, response);
	}



	@Override
	public void destroy() {

		log.debug("filter destroyed");
	}

}
