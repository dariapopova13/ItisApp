package com.university.itis.itisapp.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
@PropertySource(value = {"classpath:hibernate.properties"})
@EnableJpaRepositories(basePackages = "com.university.itis.itisapp.repository")
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.university.itis.itisapp.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

//    @Bean
//    public BasicDataSource dataSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
//                + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
//
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(dbUrl);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//
//        return basicDataSource;
//    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, PostgreSQL95Dialect.class);
        properties.put(AvailableSettings.SHOW_SQL, false);
//        properties.put(AvailableSettings.HBM2DDL_AUTO, "create");
        properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);
//
//        properties.put("hibernate.search.default.directory_provider", "filesystem");
//        properties.put("hibernate.search.default.indexBase",
//                "/home/ubuntu/.itisApp/lucene/indexes");

//        properties.put("hibernate.search.default.indexBase",
//                System.getProperty("user.home")+ "/.itisApp/lucene/indexes");

        return properties;
    }
}

