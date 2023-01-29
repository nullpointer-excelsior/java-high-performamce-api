package com.benjamin.streamdata.infraestructure.adapters;

import com.benjamin.streamdata.core.domain.interfaces.ProductRepository;
import com.benjamin.streamdata.core.domain.model.Product;
import com.benjamin.streamdata.infraestructure.persistence.entities.ProductEntity;
import com.benjamin.streamdata.infraestructure.persistence.repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    @Autowired
    private JpaProductRepository repository;

    @Override
    public List<Product> findByPaginated(int limit, int offset) {
        return repository.findByPaginated(limit, offset)
                .stream()
                .map(this::mapToProduct)
                .toList();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToProduct)
                .toList();
    }

    private Product mapToProduct(ProductEntity entity) {
        return Product.builder()
                .sku(entity.getSku())
                .stock(entity.getStock())
                .brand(entity.getBrand())
                .department(entity.getDepartment())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }

}
