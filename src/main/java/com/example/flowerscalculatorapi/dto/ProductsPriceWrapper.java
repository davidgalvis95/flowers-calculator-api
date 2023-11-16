package com.example.flowerscalculatorapi.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProductsPriceWrapper {
    List<ProductPriceDto> products;
}
