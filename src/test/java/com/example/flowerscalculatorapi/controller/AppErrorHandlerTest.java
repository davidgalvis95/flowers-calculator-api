package com.example.flowerscalculatorapi.controller;

import com.example.flowerscalculatorapi.dto.StandardResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppErrorHandlerTest {
    private final AppErrorHandler appErrorHandler = new AppErrorHandler();

    @Test
    void handleConstraintViolationException() {
        ConstraintViolationException ex = new ConstraintViolationException("Validation failed", null);

        ResponseEntity<StandardResponseDto<?>> responseEntity = appErrorHandler.handler(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Validation failed", Objects.requireNonNull(responseEntity.getBody()).getError());
    }

    @Test
    void handleRuntimeException() {
        RuntimeException ex = new RuntimeException("Runtime exception occurred");

        ResponseEntity<StandardResponseDto<?>> responseEntity = appErrorHandler.handler(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Runtime exception occurred", Objects.requireNonNull(responseEntity.getBody()).getError());
    }

    @Test
    void handleException() {
        Exception ex = new Exception("Generic exception occurred");

        ResponseEntity<StandardResponseDto<?>> responseEntity = appErrorHandler.handler(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Generic exception occurred", Objects.requireNonNull(responseEntity.getBody()).getError());
    }
}
