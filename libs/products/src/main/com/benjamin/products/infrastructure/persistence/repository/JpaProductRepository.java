package com.benjamin.products.infrastructure.persistence.repository;

import com.benjamin.products.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value="SELECT * FROM product p ORDER BY p.sku LIMIT :limit OFFSET :offset ", nativeQuery = true)
    List<ProductEntity> findByPaginated(@Param("limit")int limit, @Param("offset") int offset);

}
