package com.castillo.pavel.store.products.model.mongodb;

import com.castillo.pavel.store.products.enums.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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

}
