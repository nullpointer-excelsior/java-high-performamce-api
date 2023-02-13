package com.benjamin.client.rescontrollers;

import com.benjamin.client.model.Brand;
import com.benjamin.client.model.Product;
import com.benjamin.client.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductRestController {

    @Autowired
    private ProductService product;

    @GetMapping("brand")
    public Flux<String> getBrands() {
        return product.getBrands();
    }

    @GetMapping
    public Flux<Product> getAllProducts() {
        return product.getAllProducts();
    }

    @GetMapping("no-stock")
    public Flux<Product> getProductWithoutStock() {
        return product.getProductWithoutStock();
    }

    @GetMapping("no-stock/count")
    public Mono<Long> countSkuWithoutStock() {
        return product.countSkuWithoutStock();
    }

    public Mono<Integer> sumStockDepartment604() {
        return product.sumStockDepartment604();
    }

    public Flux<Brand> groupByDepartment604Brand() {
        return product.groupByDepartment604Brand();
    }

    @GetMapping("count")
    public Mono<Long> getCount() {
        return product.getCount();
    }

}
