package com.example.springjwt.config;

import com.example.springjwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    // 의존성 주입 -> 생성자 주입 -> @RequiredArgsConstructor

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF : Cross-Site-Request-Forgery -> 다른 페이지에서 폼 등의 방식으로 post 등의 주요 요청을 보내는 공격. (사칭)
        // CORS는 출처(origin) / 실제 유저 -> 다른 사이트인 척을 하는 사이트 -> 실제 사이트
        // https://developer.mozilla.org/ko/docs/Glossary/CSRF
        // Spring Security는 Thymeleaf 등과 사용을 하면 CSRF 토큰이라는 걸 기본적인 걸로 채택
        // -> token할 때는 굳이 csrf가 필요가 없음.
//        http.csrf(csrf -> csrf.disable());
        // CSRF 비활성화
        http.csrf(AbstractHttpConfigurer::disable);
        // 세션 비활성화
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 폼 로그인 비활성화
//        http.formLogin(form -> form.disable());
        http.formLogin(AbstractHttpConfigurer::disable);
        // HTTP Basic 인증 방식 비활성화
//        http.httpBasic(basic -> basic.disable());
        http.httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
