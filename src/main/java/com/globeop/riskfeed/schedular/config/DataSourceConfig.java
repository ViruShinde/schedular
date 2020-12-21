package com.globeop.riskfeed.schedular.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(basePackages={"${spring.data.jpa.repository.packages}"},
entityManagerFactoryRef = "appEntityManagerFactory",
transactionManagerRef = "appTransactionManager")
public class DataSourceConfig {


	@Primary
	@Bean(name="appDBSource")
	@ConfigurationProperties(prefix="app.datasource")
	public DataSource securityDataSource() {
		System.out.println(" <<<<<<<<<<<<<< appDBSource.datasource >>>>>>>>>>");
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="appEntityManagerFactory")
	@ConfigurationProperties(prefix="spring.data.jpa.entity")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("appDBSource") DataSource appDataSource,EntityManagerFactoryBuilder builder) {
		return builder
				//.dataSource(appDataSource)
				.dataSource(appDataSource)
				.packages("${spring.data.jpa.repository.packages}")
				.persistenceUnit("securityDataSource")
				.build();
	}
	
	@Primary
	@Bean(name="appTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("appEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	
}
