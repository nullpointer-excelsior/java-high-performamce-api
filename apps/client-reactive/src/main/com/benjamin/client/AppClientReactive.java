package com.benjamin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@Configuration
@SpringBootApplication
public class AppClientReactive {


    public static void main(String[] args) {
        SpringApplication.run(AppClientReactive.class, args);
    }

    @Bean
    public WebClient getWebClient() {
        return WebClient.create("http://localhost:8080");
    }


    @EventListener(ApplicationReadyEvent.class)
    public void contextRefreshedEvent() {

    

    }
}
