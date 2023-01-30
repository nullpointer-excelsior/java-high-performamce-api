package com.benjamin.streamdata.core.application;

import com.benjamin.streamdata.core.domain.model.Product;
import com.benjamin.streamdata.core.domain.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Service
public class ProductUseCases {

    @Autowired
    private ProductService products;

    public List<String> getBrands() {
        return products.getStream()
                .map(Product::getBrand)
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

    public Map<String, List<Product>> groupByDepartment604Brand() {
        return this.products.getStream()
                .filter(product -> product.getDepartment().equals("604"))
                .collect(Collectors.groupingBy(p -> p.getBrand() != null ? p.getBrand() : "No brand"));
    }

    public long getCount() {
        return products.getStream().count();
    }

}
