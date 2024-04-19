package com.example.demo.model.response;

public record TokenResponse(String token, String refreshToken, long expiresIn) {

}
