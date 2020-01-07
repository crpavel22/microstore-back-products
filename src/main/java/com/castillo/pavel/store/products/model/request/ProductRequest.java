package com.castillo.pavel.store.products.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class ProductRequest {

    @ApiModelProperty(notes = "Name of the product.", name = "name", example = "MacBookPro", required = true)
    @NotBlank(message = "Name is mandatory")
    @NonNull
    private String name;

    @ApiModelProperty(notes = "Description of the product.", name = "description", example = "Brand Apple laptop", required = true)
    @NotBlank(message = "Description is mandatory")
    @NonNull
    private String description;

}
