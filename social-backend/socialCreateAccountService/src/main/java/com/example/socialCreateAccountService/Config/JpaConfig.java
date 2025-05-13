package com.example.socialCreateAccountService.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"com.example.socialCreateAccountService.Entity"})
@EnableJpaRepositories(basePackages = {"com.example.socialCreateAccountService.Repo"})
public class JpaConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/travel_project")
                .username("root")
                .password("raveen007")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
    /*
    * This method defines a DataSource bean, which is responsible for connecting to the MySQL database.
It uses DataSourceBuilder to create an instance with:
JDBC URL → "jdbc:mysql://localhost:3306/travel_project" (Database connection URL)
Username → "root"
Password → "raveen007"
Driver Class Name → "com.mysql.cj.jdbc.Driver" (MySQL JDBC Driver)
This bean will be used by Spring Boot for database interactions.
    * */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource); // Inject DataSource via method parameter
        em.setPackagesToScan("com.example.socialCreateAccountService.Entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }
    /*
    * This method defines a LocalContainerEntityManagerFactoryBean, which is responsible for managing JPA entities.
setDataSource(dataSource) → Injects the DataSource bean to establish the connection.
setPackagesToScan("com.example.socialCreateAccountService.Entity") → Tells Spring to look for JPA entity classes in this package.
setJpaVendorAdapter(new HibernateJpaVendorAdapter()) → Configures Hibernate as the JPA provider.
    * */
    @Bean
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
    /*
    * A transaction is a sequence of database operations (like INSERT, UPDATE, DELETE) that must be executed together. If any operation fails, the transaction should rollback, ensuring data consistency.

The JpaTransactionManager is a Spring-provided class that helps manage transactions for JPA-based persistence. It ensures that:

All database operations within a transaction are committed if successful.
If an error occurs, the transaction rolls back to prevent partial changes.*/
    /*
    * Without a transaction manager, each database operation would be executed separately, which could lead to data inconsistencies. The transaction manager ensures: ✅ Atomicity – All steps of a transaction complete, or none do.*/
    /*
    * When using Spring Data JPA, the transaction manager works automatically with @Transactional annotations.*/
    /*
    * Defines a JpaTransactionManager, which is required for managing transactions in Spring Data JPA.
It is linked to the entityManagerFactory so that all JPA transactions are handled correctly.*/

//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.example.socialCreateAccountService.Entity");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
}