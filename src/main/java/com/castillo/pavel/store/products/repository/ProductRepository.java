package com.castillo.pavel.store.products.repository;

import com.castillo.pavel.store.products.model.mongodb.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);

    List<Product> findByDescription(String description);

}
