package com.castillo.pavel.store.products.controller;

import com.castillo.pavel.store.products.enums.StatusEnum;
import com.castillo.pavel.store.products.model.mongodb.Product;
import com.castillo.pavel.store.products.model.request.ProductRequest;
import com.castillo.pavel.store.products.model.response.ProductDeleteResponse;
import com.castillo.pavel.store.products.model.response.ProductListResponse;
import com.castillo.pavel.store.products.model.response.ProductResponse;
import com.castillo.pavel.store.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/api/v1.0/product")
public class ProductsController {

    private ProductService productService;


    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ProductListResponse findAll() {
        return new ProductListResponse(HttpStatus.OK, productService.findAll());
    }

    @GetMapping("/{product_id}")
    public ProductResponse findOne(@Validated @NotNull @PathVariable("product_id") String id) {
        return new ProductResponse(HttpStatus.OK, productService.findOne(id));
    }

    @GetMapping("/{product_id}/changeStatus")
    public ProductResponse changeStatus(@Validated @NotNull @PathVariable("product_id") String id) {
        return new ProductResponse(HttpStatus.OK, productService.changeStatus(id));
    }

    @DeleteMapping("/{product_id}")
    public ProductDeleteResponse delete(@Validated @NotNull @PathVariable("product_id") String id) {
        productService.delete(id);

        return new ProductDeleteResponse(HttpStatus.OK, id, "Product successfully deleted");
    }

    @PostMapping()
    public ResponseEntity<Product> addNew(@Validated @RequestBody ProductRequest productRequest) throws URISyntaxException {

        Product p = productService.save(new Product(productRequest.getName(), productRequest.getDescription(), StatusEnum.ACTIVE));

        return ResponseEntity.created(
                new URI(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + p.getProductId()).toUriString())
        )
                .body(p);
    }

}
