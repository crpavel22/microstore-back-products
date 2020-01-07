package com.castillo.pavel.store.products.controller;

import com.castillo.pavel.store.products.exception.ProductNotFoundException;
import com.castillo.pavel.store.products.model.response.ErrorResponse;
import com.castillo.pavel.store.products.model.response.ValidationErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomisedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), uri);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleAll(ProductNotFoundException ex, WebRequest request) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), uri);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        //super.handleMethodArgumentNotValid(ex, headers, status, request);
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorResponse> handleAll(RuntimeException ex, WebRequest request) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString();

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), uri);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }



}
