package com.castillo.pavel.store.products.service.impl;

import com.castillo.pavel.store.products.enums.StatusEnum;
import com.castillo.pavel.store.products.exception.ProductNotFoundException;
import com.castillo.pavel.store.products.model.mongodb.Product;
import com.castillo.pavel.store.products.repository.ProductRepository;
import com.castillo.pavel.store.products.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, String id) {

        return productRepository.findById(id)
                .map(p -> {
                    product.setProductId(p.getProductId());
                    return productRepository.save(product);
                }).orElseThrow(
                        () -> new ProductNotFoundException(id)
                );
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(String id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(id)
                );
    }

    @Override
    public void delete(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public Product changeStatus(String id) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setStatus(p.getStatus() == StatusEnum.ACTIVE ? StatusEnum.INACTIVE : StatusEnum.ACTIVE);
                    return productRepository.save(p);
                }).orElseThrow(
                        () -> new ProductNotFoundException(id)
                );
    }
}
