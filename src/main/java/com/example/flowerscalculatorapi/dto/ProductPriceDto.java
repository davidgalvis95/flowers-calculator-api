package com.example.flowerscalculatorapi.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductPriceDto {
    String productName;
    String companyName;
    String price;
}
