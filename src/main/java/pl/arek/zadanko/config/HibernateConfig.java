/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class HibernateConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String jdbcURL;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public SessionFactory getSessionFactory() {
        LocalSessionFactoryBuilder builder
                = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("pl.arek.zadanko.model")
                .addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = null;
        try {
            prop = PropertiesLoaderUtils.loadAllProperties("database.properties");
            return prop;
        } catch (IOException ex) {
            Logger.getLogger(HibernateConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prop;
    }

    @Bean
    public HibernateTransactionManager txManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(this.getSessionFactory());
        return hibernateTransactionManager;
    }

    @Bean
    public BasicDataSource dataSource() {

        BasicDataSource dataSources = new BasicDataSource();
        dataSources.setDriverClassName(this.driverClassName);
        dataSources.setUrl(this.jdbcURL);
        dataSources.setUsername(this.username);
        dataSources.setPassword(this.password);

        return dataSources;
    }
}
