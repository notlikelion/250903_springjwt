package com.example.springjwt.filter;

import com.example.springjwt.util.CookieUtil;
import com.example.springjwt.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    // 모든 요청에 대해 한 번만(씩) 실행되는 필터 로직 -> 너 JWT 토큰 있니?
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // request -> header -> cookie -> accessToken
        Optional<String> accessToken = CookieUtil.getCookie(request, "accessToken");
        // request -> accessToken 쿠키를 받아오겠다
    }
}
