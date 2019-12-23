package com.castillo.pavel.store.products.controller;

import com.castillo.pavel.store.products.model.response.ProductDeleteResponse;
import com.castillo.pavel.store.products.model.response.ProductListResponse;
import com.castillo.pavel.store.products.model.response.ProductResponse;
import com.castillo.pavel.store.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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
    public ProductResponse findAll(@NotNull @PathVariable("product_id") String id) {
        return new ProductResponse(HttpStatus.OK, productService.findOne(id));
    }

    @GetMapping("/{product_id}/changeStatus")
    public ProductResponse changeStatus(@NotNull @PathVariable("product_id") String id) {
        return new ProductResponse(HttpStatus.OK, productService.changeStatus(id));
    }

    @DeleteMapping("/{product_id}")
    public ProductDeleteResponse delete(@NotNull @PathVariable("product_id") String id) {
        productService.delete(id);

        return new ProductDeleteResponse(HttpStatus.OK, id, "Product successfully deleted");
    }

}
