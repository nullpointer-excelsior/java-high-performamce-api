package com.benjamin.streamdata.core.domain.services;

import com.benjamin.streamdata.core.domain.interfaces.ProductRepository;
import com.benjamin.streamdata.core.domain.interfaces.ReactiveProductRepository;
import com.benjamin.streamdata.core.domain.libs.FluxPaginated;
import com.benjamin.streamdata.core.domain.libs.StreamPaginated;
import com.benjamin.streamdata.core.domain.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Log4j2
@Service
public class ReactiveProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ReactiveProductRepository reactiveRepo;

    public Flux<Product> getFlux() {
        int limit = 10000;
        return // Flux.fromStream(StreamPaginated.paginate(limit))
                FluxPaginated.paginate(limit)
                .doOnNext(page -> log.info("Page {}", page))
                .concatMap(page -> reactiveRepo.findByPaginated(page.getLimit(),page.getOffset()))
                        .doOnNext(page -> log.info("Values {}", page));

        //  .map(page -> repository.findByPaginated(page.getLimit(), page.getOffset()))
              // .takeWhile(records -> !records.isEmpty())
               // .flatMap(Flux::fromIterable);
    }

}
