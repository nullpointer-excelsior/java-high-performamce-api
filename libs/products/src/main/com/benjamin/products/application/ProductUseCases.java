package com.benjamin.products.application;

import com.benjamin.products.domain.Brand;
import com.benjamin.products.domain.Product;
import com.benjamin.products.domain.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ProductUseCases {

    @Autowired
    private ProductService products;

    public List<String> getBrands() {
        return products.getStream()
                .map(Product::getBrand)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }

    public Stream<Product> getAllProducts() {
        return this.products.getStream();
    }

    public Stream<Product> getSkuWithoutStock() {
        return this.products.getStream()
                .filter(product -> product.getStock() <= 0);
    }

    public long countSkuWithoutStock() {
        return this.products.getStream()
                .filter(product -> product.getStock() <= 0)
                .count();
    }

    public long sumStockDepartment604() {
        return this.products.getStream()
                .filter(product -> product.getDepartment().equals("604"))
                .map(Product::getStock)
                .reduce(0, Integer::sum)
                .longValue();
    }

    public Stream<Brand> groupByDepartment604Brand() {
        return this.products.getStream()
                .filter(product -> product.getDepartment().equals("604"))
                .collect(Collectors.groupingBy(p -> p.getBrand() != null ? p.getBrand() : "No brand"))
                .entrySet()
                .stream()
                .map(entryMap -> new Brand(entryMap.getKey(), entryMap.getValue()));
    }

    public long getCount() {
        return products.getStream().count();
    }

}
