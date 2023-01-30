package com.benjamin.streamdata.infraestructure.httpserver;

import com.benjamin.streamdata.core.application.ProductUseCases;
import com.benjamin.streamdata.core.domain.interfaces.ProductRepository;
import com.benjamin.streamdata.core.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("web/product")
public class WebProductRestController {

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
    public List<Product> getProductsWithoutStock() {
        var stock = 0;
        for (Product p: product.getSkuWithoutStock().toList()) {
            stock += p.getStock();
        }
        System.out.println("Total products: " + stock);

        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);

        var totalAges = 0;
        for(int age: ages) {
            totalAges += age;
        }

        int computedAges = ages.stream().reduce(0, (a, b) -> a + b, Integer::sum);
        System.out.println(computedAges);

        return product.getSkuWithoutStock().toList();
    }

    @GetMapping("count-no-stock")
    public long countProductsWithoutStock() {
        return product.countSkuWithoutStock();
    }

    @GetMapping("count")
    public long count(){
        return product.getCount();
    }

    @GetMapping("raw")
    public List<Product> getProductsByRaw() { return repository.findAll();}

}
