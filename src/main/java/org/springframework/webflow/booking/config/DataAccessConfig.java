package org.springframework.webflow.booking.config;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.webflow.booking.entities.Hotel;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static java.lang.String.format;



@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("classpath:application.properties")
@Slf4j
@ToString
public class DataAccessConfig {

	public static final String U_WANT_S = "It works both under Spring Boot and Spring AS App. \n So, could you read values from property file, if u want: %s";
	@Autowired
	Environment env;


	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}


	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		log.debug(format(U_WANT_S, env.getProperty("spring.jpa.show-sql", Boolean.class)));
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(getVendorAdapter());
		emf.setPackagesToScan(Hotel.class
				.getPackage()
				.getName());
		emf.setJpaProperties(additionalProperties());
		emf.setDataSource(getDS());
		emf.afterPropertiesSet();
		return emf;
	}


	private static HibernateJpaVendorAdapter getVendorAdapter() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);
		vendorAdapter.setDatabase(Database.HSQL);
		return vendorAdapter;
	}


	static Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		return properties;
	}


	private static DataSource getDS() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:hsqldb:mem:booking", "sa", "");
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		return dataSource;
	}

}
