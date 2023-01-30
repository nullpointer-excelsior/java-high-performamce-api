package com.benjamin.streamdata.infraestructure.httpserver;

import com.benjamin.streamdata.core.application.ProductUseCases;
import com.benjamin.streamdata.core.domain.interfaces.ProductRepository;
import com.benjamin.streamdata.core.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("webflux/product")
public class WebfluxProductRestController {

    @Autowired
    private ProductUseCases product;
    @Autowired
    private ProductRepository repository;

    @GetMapping("stream")
    public List<Product> getProductsByStream() {
        return product.getAllProducts().toList();
    }

    @GetMapping("brands")
    public List<String> getBrands() {
        return product.getBrands();
    }

    @GetMapping("no-stock")
    public Flux<Product> getProductsWithoutStock() {

        return Flux.fromStream(product.getSkuWithoutStock());
    }

    @GetMapping("count")
    public long count(){
        return product.getCount();
    }

    @GetMapping("raw")
    public List<Product> getProductsByRaw() { return repository.findAll();}

}
