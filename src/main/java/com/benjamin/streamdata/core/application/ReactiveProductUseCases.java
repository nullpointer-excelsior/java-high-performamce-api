package com.benjamin.streamdata.core.application;

import com.benjamin.streamdata.core.application.dto.Brand;
import com.benjamin.streamdata.core.domain.model.Product;
import com.benjamin.streamdata.core.domain.services.ReactiveProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;


@Log4j2
@Service
@AllArgsConstructor
public class ReactiveProductUseCases {

    @Autowired
    private ReactiveProductService products;

    public Flux<String> getBrands() {
        return products.getFlux()
                .map(Product::getBrand)
                .distinct();
    }

    public Flux<Product> getAllProducts() {
        return this.products.getFlux();
    }

    public Flux<Product> getProductWithoutStock() {
        return this.products.getFlux()
                .filter(product -> product.getStock() <= 0);
    }

    public Mono<Long> countSkuWithoutStock() {
        return this.products.getFlux()
                .filter(product -> product.getStock() <= 0)
                .count();
    }

    public Mono<Integer> sumStockDepartment604() {
        return this.products.getFlux()
                .filter(product -> product.getDepartment().equals("604"))
                .map(Product::getStock)
                .reduce(0, Integer::sum);
    }

    public Flux<Brand> groupByDepartment604Brand() {

        Function<Map<String, Collection<Product>>, Stream<Brand>> mapToBrand = (Map<String, Collection<Product>> map) -> map
                .entrySet()
                .stream()
                .map(Brand::fromEntrySet);

        return this.products.getFlux()
                .filter(product -> product.getDepartment().equals("604"))
                .groupBy(p -> p.getBrand() != null ? p.getBrand() : "No brand")
                .flatMap(group -> group.collectMultimap(Product::getBrand, item -> item))
                .map(mapToBrand)
                .flatMap(Flux::fromStream);

    }

    public Mono<Long> getCount() {
        return products.getFlux().count();
    }

}
