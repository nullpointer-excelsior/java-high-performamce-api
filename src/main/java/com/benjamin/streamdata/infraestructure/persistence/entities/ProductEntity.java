package com.benjamin.streamdata.infraestructure.persistence.entities;

import com.benjamin.streamdata.core.domain.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "sku")
    private Integer sku;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "department")
    private String department;

    @Column(name = "brand")
    private String brand;

    public static Product mapToDomainEntity(ProductEntity entity) {
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
