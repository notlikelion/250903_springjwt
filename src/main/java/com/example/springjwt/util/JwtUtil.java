package com.example.springjwt.util;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component // Scan -> 등록
public class JwtUtil {
    private final SecretKey secretKey; // 비밀키 -> JWT 토큰을 만들 때/ 해석할 때 쓰일 암호화 키
    private final Long accessTokenExpiration; // 만료시간
    // [생성자 주입], 필드 주입, 세터 주입 ???
    // (순환 참조 문제...)

    // application.properties 또는 yml에 있는 값을 불러오는 것.
    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.access-token-expiration}") Long accessTokenExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiration = accessTokenExpiration;
    }
}
