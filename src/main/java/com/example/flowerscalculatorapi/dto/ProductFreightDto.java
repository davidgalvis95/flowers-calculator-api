package com.example.flowerscalculatorapi.dto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductFreightDto {
    String productName;
    String basePrice;
    Double finalFreight;
}
