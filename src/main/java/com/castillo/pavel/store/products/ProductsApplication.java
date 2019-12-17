package com.castillo.pavel.store.products;

import com.castillo.pavel.store.products.model.mongodb.Product;
import com.castillo.pavel.store.products.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ProductsApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;


	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}


	@Override
	public void run(String... args) {
		productRepository.deleteAll();

		productRepository.save(new Product("Mac", "Mac computer", "Test"));
		productRepository.save(new Product("MacBook", "Mac computer", "HHHHH"));

		log.info("Fetch all products");
		log.info("--------------------");
		productRepository.findAll().forEach(x -> log.info(x.toString()));


	}
}
