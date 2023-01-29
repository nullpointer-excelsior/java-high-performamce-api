package com.benjamin.streamdata.infraestructure.httpserver;

import com.benjamin.streamdata.core.application.ProductUseCases;
import com.benjamin.streamdata.core.domain.interfaces.ProductRepository;
import com.benjamin.streamdata.core.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("product")
public class ProductRestController {

    @Autowired
    private ProductUseCases product;
    @Autowired
    private ProductRepository repository;

    @GetMapping("stream")
    public List<Product> getProductsByStream() {
        return product.getAllProducts().toList();
    }

    @GetMapping("raw")
    public List<Product> getProductsByRaw() { return repository.findAll();}

}
