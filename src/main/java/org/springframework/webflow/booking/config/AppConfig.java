package org.springframework.webflow.booking.config;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Configuration
@ComponentScan(basePackages = "org.springframework.webflow.booking")
@Import({DataAccessConfig.class, WebMvcConfig.class, WebFlowConfig.class})
@Slf4j
@ToString
public class AppConfig {

}
