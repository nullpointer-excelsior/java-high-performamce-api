package com.benjamin.client.services;

import com.benjamin.client.model.Brand;
import com.benjamin.client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductAPI api;

    public Flux<String> getBrands() {
        return api.getProducts()
                .filter(product -> product.getBrand() != null)
                .map(Product::getBrand)
                .distinct();
    }

    public Flux<Product> getAllProducts() {
        return api.getProducts();
    }

    public Flux<Product> getProductWithoutStock() {
        return api.getProducts()
                .filter(product -> product.getStock() <= 0);
    }

    public Mono<Long> countSkuWithoutStock() {
        return api.getProducts()
                .filter(product -> product.getStock() <= 0)
                .count();
    }

    public Mono<Integer> sumStockDepartment604() {
        return api.getProducts()
                .filter(product -> product.getDepartment().equals("604"))
                .map(Product::getStock)
                .reduce(0, Integer::sum);
    }

    public Flux<Brand> groupByDepartment604Brand() {

        Function<Map<String, Collection<Product>>, Stream<Brand>> mapToBrand = (Map<String, Collection<Product>> map) -> map
                .entrySet()
                .stream()
                .map(Brand::fromEntrySet);

        return api.getProducts()
                .filter(product -> product.getDepartment().equals("604"))
                .groupBy(p -> p.getBrand() != null ? p.getBrand() : "No brand")
                .flatMap(group -> group.collectMultimap(Product::getBrand, item -> item))
                .map(mapToBrand)
                .flatMap(Flux::fromStream);

    }

    public Mono<Long> getCount() {
        return api.getProducts().count();
    }
}
