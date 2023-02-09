package com.benjamin.blocking;

import com.benjamin.products.domain.Product;
import com.benjamin.products.domain.interfaces.ProductRepository;
import com.benjamin.products.infrastructure.spring.ProductConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@Log4j2
@RestController
@RequestMapping("product")
@SpringBootApplication
@Import(ProductConfiguration.class)
public class WebApplication {

    @Autowired
    private ProductRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @GetMapping
    public Stream<Product> getProducts(
            @RequestParam(value= "limit", defaultValue="100") Integer limit,
            @RequestParam(value="offset", defaultValue="0") Integer offset
    ) {
        log.info("Web-app Request limit: {}, offset: {}", limit, offset);
        return repository.findByPaginated(limit, offset).stream();
    }

}
