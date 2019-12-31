package com.castillo.pavel.store.products.model.response;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ValidationErrorResponse extends GenericResponse {

    private Map<String, String> errors;

    public ValidationErrorResponse(HttpStatus status, Map<String, String> errors) {
        super(status);
        this.errors = errors;
    }
}
