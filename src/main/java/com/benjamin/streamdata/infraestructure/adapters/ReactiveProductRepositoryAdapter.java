package com.benjamin.streamdata.infraestructure.adapters;

import com.benjamin.streamdata.core.domain.interfaces.ReactiveProductRepository;
import com.benjamin.streamdata.core.domain.model.Product;
import com.benjamin.streamdata.infraestructure.persistence.entities.ProductEntity;
import com.benjamin.streamdata.infraestructure.persistence.repository.JpaProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Log4j2
@Repository
public class ReactiveProductRepositoryAdapter implements ReactiveProductRepository {

    @Autowired
    private JpaProductRepository repository;

    @Override
    public Flux<Product> findByPaginated(int limit, int offset) {
        var records= repository.findByPaginated(limit, offset);
        if (records.isEmpty()) {
            System.out.println("Stream ended!!!!");
            return Flux.empty();
        }

        return Flux
                .fromIterable(records)
                .map(ProductEntity::mapToDomainEntity);
    }
}
