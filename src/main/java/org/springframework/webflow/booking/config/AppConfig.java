package org.springframework.webflow.booking.config;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.webflow.filter.MyFilter;

import java.util.Collections;



@Configuration
@ComponentScan(basePackages = "org.springframework.webflow.booking")
@Import({DataAccessConfig.class, WebMvcConfig.class, WebFlowConfig.class})
@Slf4j
@ToString
public class AppConfig {

	@Bean
	FilterRegistrationBean myFilterRegistration() {

		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new MyFilter());
		frb.setUrlPatterns(Collections.singletonList("/*"));
		return frb;
	}

}
