package com.benjamin.streamdata.infraestructure.spring.config;

import com.benjamin.streamdata.infraestructure.persistence.repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Autowired
    private JpaProductRepository repository;

}
