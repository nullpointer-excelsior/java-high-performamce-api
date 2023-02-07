package com.benjamin.streamdata.core.application.dto;

import com.benjamin.streamdata.core.domain.model.Product;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public record Brand(String name, List<Product> products) {

    public static Brand fromEntrySet(Map.Entry<String, Collection<Product>> entry) {
        return new Brand(entry.getKey(), entry.getValue().stream().toList());
    }
}
