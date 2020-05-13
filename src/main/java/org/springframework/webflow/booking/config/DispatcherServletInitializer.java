package org.springframework.webflow.booking.config;



import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.webflow.utils.Utls;

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
	public void onStartup(ServletContext sc) throws ServletException {

		Utls.setParams(sc);
		super.onStartup(sc);
	}

}
