package com.benjamin.streamdata.core.domain.services;

import com.benjamin.streamdata.core.domain.interfaces.ProductRepository;
import com.benjamin.streamdata.core.domain.libs.StreamPaginated;
import com.benjamin.streamdata.core.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public Stream<Product> getStream() {
        return StreamPaginated
                .paginate(10000)
                .map(page -> this.repository.findByPaginated(page.getLimit(), page.getOffset()))
                .takeWhile(records -> !records.isEmpty())
                .flatMap(Collection::stream);

    }
}
