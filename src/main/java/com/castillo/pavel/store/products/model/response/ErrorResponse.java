package com.castillo.pavel.store.products.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse extends GenericResponse {

    @ApiModelProperty(notes = "Error description.", name = "message", example = "Product not found!")
    private String message;
    @ApiModelProperty(notes = "URL of the current request.", name = "url", example = "http://localhost:8080/xxx/yyy")
    private String url;

    public ErrorResponse(HttpStatus status, String message, String url) {
        super(status);
        this.message = message;
        this.url = url;
    }
}
