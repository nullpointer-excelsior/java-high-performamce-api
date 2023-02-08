package com.benjamin.products.infrastructure.spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Import this configuration in Spring application with @Import(ProductConfiguration.class)
 */
@Configuration
@ComponentScan("com.benjamin.products")
@EnableJpaRepositories("com.benjamin.products.infrastructure.persistence.repository")
@EntityScan("com.benjamin.products.infrastructure.persistence.entities")
public class ProductConfiguration {

}
