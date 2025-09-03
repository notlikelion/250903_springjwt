package com.example.springjwt.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class CookieUtil {
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/"); // 이 경로에 있는 모든 페이지에서 이 쿠키를 쓸 수 있게
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true); // javascript 접근 금지 -> 유저와 서버만 볼 수 있게
        response.addCookie(cookie); // 응답에 추가
    }

    public static Optional<String> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie.getValue());
                    // accessToken -> value.
                }
            }
        }
        return Optional.empty();
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0); // 만료
                    response.addCookie(cookie);
                }
            }
        }
    }
}
