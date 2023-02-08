package com.benjamin.products.domain.services;

import com.benjamin.products.domain.Product;
import com.benjamin.products.domain.interfaces.ProductRepository;
import com.benjamin.products.domain.libs.StreamPaginated;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Stream<Product> getStream() {
        return StreamPaginated
                .paginate(10000)
                .map(page -> this.repository.findByPaginated(page.getLimit(), page.getOffset()))
                .takeWhile(records -> !records.isEmpty())
                .flatMap(Collection::stream);

    }
}
