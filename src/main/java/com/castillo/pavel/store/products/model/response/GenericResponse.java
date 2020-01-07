package com.castillo.pavel.store.products.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@AllArgsConstructor
public class GenericResponse {

    @ApiModelProperty(notes = "Actual date of response", name = "date", example = "Jan 02, 2020 10:10:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy HH:mm:ss")
    @JsonProperty("date")
    private final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    @ApiModelProperty(notes = "Status of the response", name = "status", example = "OK")
    @JsonProperty("status")
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }
}
