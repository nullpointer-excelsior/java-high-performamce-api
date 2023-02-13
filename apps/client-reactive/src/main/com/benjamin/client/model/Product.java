package com.benjamin.client.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Product {
    private Integer sku;
    private String name;
    private Double price;
    private Integer stock;
    private String department;
    private String brand;
}
