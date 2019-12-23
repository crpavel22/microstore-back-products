package com.castillo.pavel.store.products.service;

import com.castillo.pavel.store.products.model.mongodb.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product update(Product product, String id);

    List<Product> findAll();

    Product findOne(String id);

    void delete(String id);

    Product changeStatus(String id);
}
