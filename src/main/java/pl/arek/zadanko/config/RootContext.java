/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import pl.arek.zadanko.model.repositories.CustomerRepository;
import pl.arek.zadanko.model.repositories.impl.CustomerRepositoryImpl;
import pl.arek.zadanko.services.CustomerService;
import pl.arek.zadanko.services.RaportService;
import pl.arek.zadanko.services.impl.CustomerServiceImpl;
import pl.arek.zadanko.services.impl.RaportServiceImpl;
import pl.arek.zadanko.tools.datagenerator.SimpleDataGenerator;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
@Configuration
public class RootContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    CustomerRepository customerRepository() {
        return new CustomerRepositoryImpl();
    }

    @Bean
    SimpleDataGenerator dataGenerator() {
        return new SimpleDataGenerator();
    }

    @Bean
    CustomerService customerService() {
        return new CustomerServiceImpl();
    }

    @Bean
    RaportService raportService() {
        return new RaportServiceImpl();
    }
}
