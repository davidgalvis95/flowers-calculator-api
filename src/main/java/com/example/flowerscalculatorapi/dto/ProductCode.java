package com.example.flowerscalculatorapi.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductCode {
    String productName;
    String productCode;
}
