package org.springframework.webflow.ctx;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static org.apache.commons.lang3.StringUtils.isNotBlank;



/**
 * configure dynamic Context Path in Spring Boot
 *
 * @since 05.2020
 */
@ToString
@Slf4j
public class ServletContextFacadeRequestWrapper extends HttpServletRequestWrapper {

	private String contextPath;
	private String servletPath;


	public ServletContextFacadeRequestWrapper(HttpServletRequest request) {

		super(request);
	}


	@Override
	public String getContextPath() {

		if (isNotBlank(contextPath)) {
			return contextPath;
		}
		log.debug("\ngetContextPath: {}", super.getContextPath());
		return super.getContextPath();
	}


	public void setContextPath(String contextPath) {

		this.contextPath = contextPath;
	}


	@Override
	public String getServletPath() {

		if (isNotBlank(servletPath)) {
			return servletPath;
		}
		log.debug("\ngetServletPath: {}", super.getServletPath());
		return super.getServletPath();
	}


	public void setServletPath(String servletPath) {

		this.servletPath = servletPath;
	}


	@Override
	public String getRequestURI() {

		String requestURI = super.getRequestURI();
		if (requestURI.equals(contextPath)) {
			return requestURI + '/';
		}
		log.debug("\ngetRequestURI: {}", requestURI);
		return requestURI;
	}

}
