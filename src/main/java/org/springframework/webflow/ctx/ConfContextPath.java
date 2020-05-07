package org.springframework.webflow.ctx;



import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * configure dynamic Context Path in Spring Boot
 *
 * @since 05.2020
 */
@Configuration
public class ConfContextPath {

    @Bean
    public WrapRequestFilter wrapRequestFilter() {

        return new WrapRequestFilter();
    }



    @Bean
    public FilterRegistrationBean wrapRequestFilterRegistrationBean() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(wrapRequestFilter());
        registrationBean.setName("wrapRequestFilter"); //NON-NLS
        registrationBean.setOrder(-1000001);
        return registrationBean;
    }

}