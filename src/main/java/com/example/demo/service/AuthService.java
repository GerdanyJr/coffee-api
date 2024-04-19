package com.example.demo.service;

import java.net.URI;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.config.TokenService;
import com.example.demo.exception.ConflictException;
import com.example.demo.model.dto.Token;
import com.example.demo.model.entities.User;
import com.example.demo.model.request.LoginReq;
import com.example.demo.model.request.RegisterReq;
import com.example.demo.model.response.TokenResponse;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
            TokenService tokenService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.encoder = encoder;
    }

    public URI register(RegisterReq req) {
        Boolean userExists = userRepository.existsByUsername(req.username());
        if (userExists) {
            throw new ConflictException("Usuário já cadastrado com este username");
        }
        User createdUser = userRepository.save(new User(
                null,
                req.name(),
                req.username(),
                encoder.encode(req.password()),
                null));

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{userId}")
                .buildAndExpand(createdUser.getId())
                .toUri();
    }

    public TokenResponse login(LoginReq req) {
        var usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                req.username(),
                req.password());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordToken);
        User user = (User) authenticate.getPrincipal();
        Token accessToken = tokenService.generateAcessToken(user);
        Token refreshToken = tokenService.generateRefreshToken(user);
        return new TokenResponse(
                accessToken.token(),
                refreshToken.token(),
                accessToken.expiresIn());
    }
}
