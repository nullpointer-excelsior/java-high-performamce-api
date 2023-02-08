package com.benjamin.streamdata.core.domain.model;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Product {
    private Integer sku;
    private String name;
    private Double price;
    private Integer stock;
    private String department;
    private String brand;

}
