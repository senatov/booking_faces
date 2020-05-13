package org.springframework.webflow.ctx;



import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static org.apache.commons.lang3.StringUtils.isNotBlank;



/**
 * configure dynamic Context Path in Spring Boot
 *
 * @since 05.2020
 */
@ToString
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
        return requestURI;
    }

}
