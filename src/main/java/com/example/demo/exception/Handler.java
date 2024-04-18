package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.model.response.ErrorResponse;

@RestControllerAdvice
public class Handler {
    
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(BaseException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        e.getCode(),
                        e.getHttpStatus(),
                        LocalDateTime.now(),
                        e.getMessage()));
    }
}
