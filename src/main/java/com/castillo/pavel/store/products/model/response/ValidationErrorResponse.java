package com.castillo.pavel.store.products.model.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ValidationErrorResponse extends GenericResponse {


    @ApiModelProperty(notes = "Error of validation List", name = "errors", dataType = "Map<String,Sting>")
    private Map<String, String> errors;

    public ValidationErrorResponse(HttpStatus status, Map<String, String> errors) {
        super(status);
        this.errors = errors;
    }
}
