package org.springframework.webflow.booking.config;



import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;



public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class<?>[]{SecurityConfig.class, AppConfig.class};
    }



    @Override
    protected Class<?>[] getServletConfigClasses() {

        return null;
    }



    @Override
    protected String[] getServletMappings() {

        return new String[]{"/spring/*"};
    }



    @Override
    protected Filter[] getServletFilters() {

        return new Filter[]{new CharacterEncodingFilter()};
    }



    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.addListener(com.sun.faces.config.ConfigureListener.class);
        servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
        servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");
        servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
        super.onStartup(servletContext);
    }

}
