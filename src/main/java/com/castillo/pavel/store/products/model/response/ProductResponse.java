package com.castillo.pavel.store.products.model.response;

import com.castillo.pavel.store.products.model.mongodb.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonRootName("response")
@Getter
public class ProductResponse extends GenericResponse {

    @JsonProperty("product")
    private Product product;

    public ProductResponse(HttpStatus status, Product product) {
        super(status);
        this.product = product;
    }
}
