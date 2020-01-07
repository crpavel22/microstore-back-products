package com.castillo.pavel.store.products.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductRequest {

    @ApiModelProperty(notes = "Name of the product.", name = "name", example = "MacBookPro", required = true)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ApiModelProperty(notes = "Description of the product.", name = "description", example = "Brand Apple laptop", required = true)
    @NotBlank(message = "Description is mandatory")
    private String description;

}
