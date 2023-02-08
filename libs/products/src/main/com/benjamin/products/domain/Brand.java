package com.benjamin.products.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class Brand {
    private String name;
    private List<Product> products;
}
