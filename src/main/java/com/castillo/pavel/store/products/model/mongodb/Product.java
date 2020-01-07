package com.castillo.pavel.store.products.model.mongodb;

import com.castillo.pavel.store.products.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "products")
@TypeAlias("product")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

    @Id
    private String productId;
    @NonNull
    @Indexed(unique = true, name = "name")
    @Field("name")
    private String name;
    @NonNull
    private String description;
    @NonNull
    private StatusEnum status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy HH:mm:ss")
    private LocalDateTime createdDate = LocalDateTime.now();


}
