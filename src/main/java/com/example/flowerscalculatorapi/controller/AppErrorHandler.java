package com.example.flowerscalculatorapi.controller;

import com.example.flowerscalculatorapi.dto.StandardResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@RestControllerAdvice
public class AppErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardResponseDto<?>> handler(ConstraintViolationException ex) {
        return new ResponseEntity<>(buildStandardResponse(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardResponseDto<?>> handler(RuntimeException ex) {
        return new ResponseEntity<>(buildStandardResponse(ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponseDto<?>> handler(Exception ex) {
        return new ResponseEntity<>(buildStandardResponse(ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private StandardResponseDto<?> buildStandardResponse(final String errorMessage, final String customMessage) {
        return new StandardResponseDto<>(
                null,
                customMessage,
                errorMessage
        );
    }
}
