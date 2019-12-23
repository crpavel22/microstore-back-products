package com.castillo.pavel.store.products.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@AllArgsConstructor
public class GenericResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy HH:mm:ss")
    @JsonProperty("date")
    private final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    @JsonProperty("status")
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }
}
