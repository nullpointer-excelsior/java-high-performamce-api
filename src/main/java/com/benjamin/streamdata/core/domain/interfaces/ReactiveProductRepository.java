package com.benjamin.streamdata.core.domain.interfaces;

import com.benjamin.streamdata.core.domain.model.Product;
import reactor.core.publisher.Flux;

public interface ReactiveProductRepository {

    Flux<Product> findByPaginated(int limit, int offset);

}
