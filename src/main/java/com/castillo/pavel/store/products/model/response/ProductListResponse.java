package com.castillo.pavel.store.products.model.response;

import com.castillo.pavel.store.products.model.mongodb.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonRootName("response")
@Getter
public class ProductListResponse extends GenericResponse {

    @JsonProperty("products")
    private List<Product> products;

    public ProductListResponse(HttpStatus status, List<Product> products) {
        super(status);
        this.products = products;
    }

}
