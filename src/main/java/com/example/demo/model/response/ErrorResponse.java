package com.example.demo.model.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private Integer code;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer code, HttpStatus httpStatus, LocalDateTime timestamp, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
