package com.donedeal.schema.stripe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String name;
    private String currency;
    private Long amount;
    private Long quantity;
}
