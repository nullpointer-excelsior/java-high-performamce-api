package com.benjamin.client.services;

import com.benjamin.client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Arrays;

@Service
public class ProductAPI {

    @Autowired
    private WebClient client;

    public Flux<Product> getProducts() {
var flux = Flux.fromIterable(Arrays.asList(10,20,30,40,50));
flux.subscribe(System.out::println);
        return client.get()
                .uri("/product")
                .retrieve()
                .bodyToFlux(Product.class);

    }

}
