package com.castillo.pavel.store.products.model.response;

import com.castillo.pavel.store.products.model.mongodb.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@JsonRootName("response")
@Getter
public class ProductListResponse extends GenericResponse {

    @ApiModelProperty(notes = "List of products on the system", name = "products")
    @JsonProperty("products")
    private List<Product> products;

    public ProductListResponse(HttpStatus status, List<Product> products) {
        super(status);
        this.products = products;
    }

}
