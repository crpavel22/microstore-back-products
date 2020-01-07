package com.castillo.pavel.store.products.runner;

import com.castillo.pavel.store.products.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandRunner implements CommandLineRunner {


    private ProductRepository productRepository;

    @Autowired
    public CommandRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
       // productRepository.deleteAll();

//        Product p = new Product("Test", "Test", StatusEnum.ACTIVE);
//        productRepository.save(new Product("Mac", "Mac computer", StatusEnum.ACTIVE));
//        productRepository.save(new Product("MacBook", "Mac computer", StatusEnum.ACTIVE));
//        p = productRepository.save(p);
//
//        log.debug("Fetch all products");
//        log.debug("--------------------");
//        productRepository.findAllByStatusOrderByProductIdDesc(StatusEnum.ACTIVE).forEach(x -> log.info(x.toString()));
//        productRepository.findById(p.getProductId() == null ? "dd" : p.getProductId());
//        productRepository.findByNameLikeAndStatusOrderByName("t", StatusEnum.INACTIVE)
//        ;

        log.info(String.valueOf(productRepository.count()));

    }
}
