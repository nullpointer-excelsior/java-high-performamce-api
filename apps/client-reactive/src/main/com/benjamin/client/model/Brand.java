package com.benjamin.client.model;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private String name;
    private List<Product> products;

    public static Brand fromEntrySet(Map.Entry<String, Collection<Product>> entry) {
        return new Brand(entry.getKey(), entry.getValue().stream().toList());
    }
}
