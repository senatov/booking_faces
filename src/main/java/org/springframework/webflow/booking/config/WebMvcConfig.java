package org.springframework.webflow.booking.config;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.faces.webflow.JsfFlowHandlerAdapter;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import static java.util.concurrent.TimeUnit.HOURS;



@Configuration
@Slf4j
@ToString
public class WebMvcConfig implements WebMvcConfigurer {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/resources/", "classpath:/public/", "classpath:/public/images/", "classpath:/public/styles/"};

	public static final CacheControl CACHE_PUBLIC = CacheControl
			.maxAge(2L, HOURS)
			.cachePublic();
	@Autowired
	private WebFlowConfig webFlowConfig;



	@Bean
	public FlowHandlerMapping flowHandlerMapping() {

		FlowHandlerMapping mapping = new FlowHandlerMapping();
		mapping.setOrder(1);
		mapping.setFlowRegistry(webFlowConfig.flowRegistry());
		/* If no flow matches, map the path to a view, e.g. "/intro" maps to a view named "intro" */
		mapping.setDefaultHandler(new UrlFilenameViewController());
		return mapping;
	}



	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {

		JsfFlowHandlerAdapter adapter = new JsfFlowHandlerAdapter();
		adapter.setFlowExecutor(webFlowConfig.flowExecutor());
		return adapter;
	}



	@Bean
	public UrlBasedViewResolver faceletsViewResolver() {

		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(JsfView.class);
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".xhtml");
		return resolver;
	}



	@Bean
	public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {

		return new SimpleControllerHandlerAdapter();
	}

}
