package com.castillo.pavel.store.products;

import com.castillo.pavel.store.products.model.request.ProductRequest;
import com.castillo.pavel.store.products.model.response.ProductListResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT)
@Profile({"hot-box", "local"})
public class IntegrationProductTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllProducts() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/api/v1.0/product", ProductListResponse.class)
                        .getProducts().size() == 0
        );
    }

    @Test
    public void testAddProduct() {
        ProductRequest pr = new ProductRequest("Integration Test", "Test about integration");
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/v1.0/product", pr, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}
