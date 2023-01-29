package com.benjamin.streamdata.core.application;

import com.benjamin.streamdata.core.domain.model.Product;
import com.benjamin.streamdata.core.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

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

    public long getCount() {
        return products.getStream().count();
    }

}
