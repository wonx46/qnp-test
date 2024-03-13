package com.qnp.iwan;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		  entityManagerFactoryRef = "qnpEntityManagerFactory",
		  transactionManagerRef = "qnpTransactionManager",
		  basePackages = { "com.qnp.iwan.repository" }
		)
public class DBConfig {
	
	  @Primary
	  @Bean(name = "qnpdataSource")
	  @ConfigurationProperties(prefix = "spring.datasource.qnp")
	  public DataSource dataSource() {
	    return DataSourceBuilder.create().build();
	  }
	  
	  @Primary
	  @Bean(name = "qnpEntityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean 
	  entityManagerFactory(
	    EntityManagerFactoryBuilder builder,
	    @Qualifier("qnpdataSource") DataSource dataSource
	  ) {
	    return builder
	      .dataSource(dataSource)
	      .packages("com.qnp.iwan.model")
	      .persistenceUnit("qnp")
	      .build();
	  }
	    
	  @Primary
	  @Bean(name = "qnpTransactionManager")
	  public PlatformTransactionManager transactionManager(
	    @Qualifier("qnpEntityManagerFactory") EntityManagerFactory 
	    qnpentityManagerFactory
	  ) {
	    return new JpaTransactionManager(qnpentityManagerFactory);
	  }
}
