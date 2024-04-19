package com.example.demo.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.model.dto.Token;
import com.example.demo.model.entities.User;

@Component
public class TokenService {

    @Value("${jwt.accessToken.secret}")
    private String TOKENSECRET;

    @Value("${jwt.issuer}")
    private String ISSUER;

    @Value("${jwt.accessToken.expirationTime}")
    private Long ACCESSTOKENEXPIRATIONTIME;

    @Value("${jwt.refreshToken.expirationTime}")
    private Long REFRESHTOKENEXPIRATIONTIME;

    public Token generateAcessToken(User user) {
        String acessToken = JWT
                .create()
                .withIssuer(ISSUER)
                .withExpiresAt(getExpiresAt(ACCESSTOKENEXPIRATIONTIME))
                .withClaim("accessToken", "true")
                .withSubject(user.getUsername())
                .sign(Algorithm.HMAC256(TOKENSECRET));
        return new Token(acessToken, ACCESSTOKENEXPIRATIONTIME);
    }

    public Token generateRefreshToken(User user) {
        String acessToken = JWT
                .create()
                .withIssuer(ISSUER)
                .withExpiresAt(getExpiresAt(REFRESHTOKENEXPIRATIONTIME))
                .withClaim("refreshToken", "true")
                .withSubject(user.getUsername())
                .sign(Algorithm.HMAC256(TOKENSECRET));
        return new Token(acessToken, REFRESHTOKENEXPIRATIONTIME);
    }

    public String getRefreshTokenSubject(String refreshToken) {
        return JWT
                .require(Algorithm.HMAC256(TOKENSECRET))
                .withIssuer(ISSUER)
                .withClaim("refreshToken", true)
                .build().verify(getToken(refreshToken))
                .getSubject();
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(TOKENSECRET))
                .withIssuer(ISSUER)
                .withClaim("accessToken", "true")
                .build().verify(getToken(token))
                .getSubject();
    }

    private Instant getExpiresAt(Long expirationTime) {
        return Instant.now().plusSeconds(expirationTime);
    }

    private String getToken(String token) {
        return token.replace("Bearer ", "");
    }
}
