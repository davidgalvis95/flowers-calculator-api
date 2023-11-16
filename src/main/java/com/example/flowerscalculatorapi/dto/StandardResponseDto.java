package com.example.flowerscalculatorapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StandardResponseDto<T> {
    private T payload;
    private String message;
    private String error;
}
