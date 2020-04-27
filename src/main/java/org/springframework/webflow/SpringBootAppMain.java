package org.springframework.webflow;



import com.sun.faces.config.ConfigureListener;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;
import javax.naming.NamingException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static java.lang.String.valueOf;



@Slf4j
@ToString
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootAppMain implements CommandLineRunner {

    public static final String FORMAT1 = "  %s)  [ %s ]  ";
    @Autowired
    private ApplicationContext appContext;



    public static void main(String... args) throws Exception {
    
        SpringApplication.run(SpringBootAppMain.class, args);
    }



    @Override
    public void run(String... args) throws Exception {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        Arrays.stream(appContext.getBeanDefinitionNames()) //
              .sorted()//
              .forEach(o -> {
                  String count = valueOf(atomicInteger.getAndDecrement());
                  log.debug(format(FORMAT1, count, o));
              });
    }



    @Bean
    public ServletRegistrationBean<FacesServlet> facesServletRegistraiton() {

        ServletRegistrationBean servletRegistration = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
        servletRegistration.setLoadOnStartup(1);
        return servletRegistration;
    }



    @Bean
    public ServletContextInitializer servletContextInitializer() throws NamingException {

        log.debug("servletContextInitializer()");
        return sc -> {
            sc.addListener(ConfigureListener.class);
            sc.setInitParameter("com.sun.faces.expressionFactory", "org.apache.el.ExpressionFactoryImpl");
            sc.setInitParameter("com.sun.faces.forceLoadConfiguration", TRUE.toString());
            sc.setInitParameter("facelets.DEVELOPMENT", TRUE.toString());
            sc.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
            sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", FALSE.toString());
            sc.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", TRUE.toString());
            sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
            sc.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
            sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", TRUE.toString());
            sc.setInitParameter("primefaces.FONT_AWESOME", TRUE.toString());
            sc.setInitParameter("primefaces.THEME", "redmond");
            sc.setInitParameter("javax.faces.WEBAPP_RESOURCES_DIRECTORY", "/WEB-INF/static");
        };
    }

    
}