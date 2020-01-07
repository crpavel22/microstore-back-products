package com.castillo.pavel.store.products.controller;

import com.castillo.pavel.store.products.enums.StatusEnum;
import com.castillo.pavel.store.products.model.mongodb.Product;
import com.castillo.pavel.store.products.model.request.ProductRequest;
import com.castillo.pavel.store.products.model.response.*;
import com.castillo.pavel.store.products.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

@Api(value = "Products API")
@RestController
@RequestMapping(value = "/api/v1.0/product")
public class ProductsController {

    private final ProductService productService;


    public ProductsController(final ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Get a list of all the products in the system", response = ProductListResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = ProductListResponse.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "forbidden!!!!", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "not found!", response = ErrorResponse.class),

    })
    @GetMapping()
    public ProductListResponse findAll() {
        return new ProductListResponse(HttpStatus.OK, productService.findAll());
    }

    @ApiOperation(value = "Get a specific product based on his ID", response = ProductResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "forbidden!!!!", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "not found!", response = ErrorResponse.class),

    })
    @GetMapping("/{product_id}")
    public ProductResponse findOne(@Validated @NotNull @PathVariable("product_id") String id) {
        return new ProductResponse(HttpStatus.OK, productService.findOne(id));
    }

    @ApiOperation(value = "Change status of a specific product based on his ID", response = ProductResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "forbidden!!!!", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "not found!", response = ErrorResponse.class),

    })
    @PutMapping("/{product_id}/changeStatus")
    public ProductResponse changeStatus(@Validated @NotNull @PathVariable("product_id") String id) {
        return new ProductResponse(HttpStatus.OK, productService.changeStatus(id));
    }

    @ApiOperation(value = "Remove a specific product based on his ID", response = ProductDeleteResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = ProductDeleteResponse.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "forbidden!!!!", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "not found!", response = ErrorResponse.class),

    })
    @DeleteMapping("/{product_id}")
    public ProductDeleteResponse delete(@Validated @NotNull @PathVariable("product_id") String id) {
        productService.delete(id);

        return new ProductDeleteResponse(HttpStatus.OK, id, "Product successfully deleted");
    }

    @ApiOperation(value = "Add a product in the system.", response = ProductResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created!", response = ProductResponse.class, responseHeaders = {
                    @ResponseHeader(name = "Location", description = "Location of the new Resource")
            }),
            @ApiResponse(code = 401, message = "not authorized!", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "forbidden!!!!", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "not found!", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Bad Request!!", response = ValidationErrorResponse.class)

    })
    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Validated @RequestBody ProductRequest productRequest) throws URISyntaxException {

        Product p = productService.save(new Product(productRequest.getName(), productRequest.getDescription(), StatusEnum.ACTIVE));

        return ResponseEntity.created(
                new URI(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + p.getProductId()).toUriString())
        )
                .body(new ProductResponse(HttpStatus.CREATED, p));
    }


    @ApiOperation(value = "Update a specific product based on his ID", response = ProductResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "not authorized!", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "forbidden!!!!", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "not found!", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Bad Request!!", response = ValidationErrorResponse.class)

    })
    @PutMapping("/{product_id}")
    public ResponseEntity<ProductResponse> update(@Validated @RequestBody ProductRequest productRequest, @PathVariable("product_id") String id) throws URISyntaxException {


        Product p = productService.update(new Product(productRequest.getName(), productRequest.getDescription(), StatusEnum.ACTIVE), id);


        return ResponseEntity.created(
                new URI(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString())
        )
                .body(new ProductResponse(HttpStatus.CREATED, p));
    }

}
