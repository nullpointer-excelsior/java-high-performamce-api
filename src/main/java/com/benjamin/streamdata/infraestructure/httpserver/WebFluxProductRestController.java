package com.benjamin.streamdata.infraestructure.httpserver;

import com.benjamin.streamdata.core.application.StreamProductUseCases;
import com.benjamin.streamdata.core.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;


@RestController
@RequestMapping("product")
public class WebFluxProductRestController {

    @Autowired
    private StreamProductUseCases products;

    @GetMapping
    public Flux<Product> getProducts() {
        return Flux.fromStream(products.getAllProducts());
    }

    @GetMapping("no-stock")
    public Flux<Product> getProductWithoutStock() {
       return Flux.fromStream(products.getSkuWithoutStock())
               .retryWhen(Retry.fixedDelay(100, Duration.ofSeconds(5)));
    }


}
