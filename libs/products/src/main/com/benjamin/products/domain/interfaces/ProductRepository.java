package com.benjamin.products.domain.interfaces;

import com.benjamin.products.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findByPaginated(int limit, int offset);

}
