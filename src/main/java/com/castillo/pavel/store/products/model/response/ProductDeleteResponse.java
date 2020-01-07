package com.castillo.pavel.store.products.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonRootName("response")
@Getter
public class ProductDeleteResponse extends GenericResponse {

    @ApiModelProperty(notes = "Id of the product deleted.", name = "productId", example = "928374637382927")
    @JsonProperty("productId")
    private String id;
    @ApiModelProperty(notes = "Description of the operation realized.", name = "message", example = "success deleted!")
    @JsonProperty("message")
    private String msg;

    public ProductDeleteResponse(HttpStatus status, String id, String msg) {
        super(status);
        this.id = id;
        this.msg = msg;
    }
}
