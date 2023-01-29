package com.benjamin.streamdata.core.domain.interfaces;

import com.benjamin.streamdata.core.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findByPaginated(int limit, int offset);

    List<Product> findAll();
}
