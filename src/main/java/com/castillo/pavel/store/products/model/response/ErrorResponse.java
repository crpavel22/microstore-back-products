package com.castillo.pavel.store.products.model.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse extends GenericResponse {

    private String message;
    private String url;

    public ErrorResponse(HttpStatus status, String message, String url) {
        super(status);
        this.message = message;
        this.url = url;
    }
}
