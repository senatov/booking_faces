package org.springframework.webflow.booking.config;



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
public class WebMvcConfig implements WebMvcConfigurer {

    public static final CacheControl CACHE_PUBLIC = CacheControl.maxAge(2, HOURS).cachePublic();
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

    /**
     * Register resource handler for images, scrips and css under /webapp in Spring-Boot start configuration.
     *
     * @param registry
     * @since 05.2020
     */
    /**
     @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {

     registry
     .addResourceHandler("/images/**")
     .addResourceLocations("/images/")
     .setCacheControl(CACHE_PUBLIC);
     registry
     .addResourceHandler("/styles/**")
     .addResourceLocations("/styles/")
     .setCacheControl(CACHE_PUBLIC);
     //registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/spring/").setCacheControl(CACHE_PUBLIC);
     log.debug(format("registry= %s", registry));
     }
     **/
}
