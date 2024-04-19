package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.LoginReq;
import com.example.demo.model.request.RegisterReq;
import com.example.demo.model.response.TokenResponse;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterReq req) {
        return ResponseEntity.created(authService.register(req)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginReq req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @GetMapping("/teste")
    public String authenticado() {
        return "Autenticado";
    }
}
