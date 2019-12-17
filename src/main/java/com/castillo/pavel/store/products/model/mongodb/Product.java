package com.castillo.pavel.store.products.model.mongodb;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    private String productId;
    private String name;
    private String description;

    public Product(String name, String description, String other) {
        this.name = name;
        this.description = description;
    }
}
