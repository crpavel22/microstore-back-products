package com.castillo.pavel.store.products.model.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement
public class ErrorResponse extends GenericResponse {

    private String message;
    private String url;

    public ErrorResponse(HttpStatus status, String message, String url) {
        super(status);
        this.message = message;
        this.url = url;
    }
}
