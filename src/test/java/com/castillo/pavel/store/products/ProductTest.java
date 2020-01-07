package com.castillo.pavel.store.products;

import com.castillo.pavel.store.products.enums.StatusEnum;
import com.castillo.pavel.store.products.exception.ProductNotFoundException;
import com.castillo.pavel.store.products.model.mongodb.Product;
import com.castillo.pavel.store.products.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class ProductTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    @Test
    public void test404() throws Exception {
        doThrow(ProductNotFoundException.class)
                .when(productService).findOne(anyString());
        mockMvc.perform(get("/api/v1.0/product/error")).andExpect(status().isNotFound());
    }

    @Test
    public void should_Not_CreateProduct_When_InvalidRequest() throws Exception {
        Product p = new Product("Test Update", "Test Update Description", StatusEnum.ACTIVE);
        p.setProductId("123456789");

        when(productService.save(any(Product.class))).thenReturn(p);

        mockMvc.perform(post("/api/v1.0/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"g\":\"Test Update\"," +
                        "\"description\":\"Test Update Description\"" +
                        "}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").value("Name is mandatory"))
                .andExpect(jsonPath("$.errors.description").doesNotExist())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void should_CreateProduct_When_InvalidRequest() throws Exception {
        Product p = new Product("Test Update", "Test Update Description", StatusEnum.ACTIVE);
        p.setProductId("123456789");

        when(productService.save(any(Product.class))).thenReturn(p);

        mockMvc.perform(post("/api/v1.0/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"name\":\"Test Update\"," +
                        "\"description\":\"Test Update Description\"" +
                        "}")
        )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.product.productId").value("123456789"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void should_DeleteProduct_When_Correct_ID() throws Exception {
        mockMvc.perform(delete("/api/v1.0/product/asdfasdfasdfasdf"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_404_when_DeleteProduct_When_Incorrect_ID() throws Exception {
        doThrow(ProductNotFoundException.class).when(productService).delete(anyString());

        mockMvc.perform(delete("/api/v1.0/product/asdfasdfasdfasdf"))
                .andExpect(status().isNotFound());
    }
}
