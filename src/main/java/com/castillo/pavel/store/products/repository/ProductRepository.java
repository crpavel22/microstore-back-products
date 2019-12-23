package com.castillo.pavel.store.products.repository;

import com.castillo.pavel.store.products.enums.StatusEnum;
import com.castillo.pavel.store.products.model.mongodb.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);

    Product findByNameLikeAndStatusOrderByName(String name, StatusEnum statusEnum);

    List<Product> findByDescription(String description);

    List<Product> findAllByStatusOrderByProductIdDesc(StatusEnum statusEnum);

}
