package com.castillo.pavel.store.products.model.response;

import com.castillo.pavel.store.products.model.mongodb.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@JsonRootName("response")
@Getter
public class ProductResponse extends GenericResponse {

    @ApiModelProperty(notes = "Product retrieved of the system", name = "product")
    @JsonProperty("product")
    private Product product;

    public ProductResponse(HttpStatus status, Product product) {
        super(status);
        this.product = product;
    }
}
