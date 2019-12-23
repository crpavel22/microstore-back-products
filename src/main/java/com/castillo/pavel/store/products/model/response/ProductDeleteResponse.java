package com.castillo.pavel.store.products.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonRootName("response")
@Getter
public class ProductDeleteResponse extends GenericResponse {

    @JsonProperty("productId")
    private String id;
    @JsonProperty("message")
    private String msg;

    public ProductDeleteResponse(HttpStatus status, String id, String msg) {
        super(status);
        this.id = id;
        this.msg = msg;
    }
}
