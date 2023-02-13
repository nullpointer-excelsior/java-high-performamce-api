package com.benjamin.client.services;

import com.benjamin.client.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Log4j2
@Service
public class ProductAPI {

    @Autowired
    private WebClient client;

    public Flux<Product> getProducts() {

        return client.get()
                .uri("/product")
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnSubscribe(x -> log.info("Subscription to api-webflux successful"))
                .doOnError(err -> log.error("Error getting products from API", err))
                .doOnComplete(() ->  log.info("Products Flux terminated"));

    }

}
