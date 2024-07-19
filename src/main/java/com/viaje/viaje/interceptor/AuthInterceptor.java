package com.viaje.viaje.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 사용자 정보 가져오기
        String email = (String) request.getSession().getAttribute("user");

        // 로그인 되어 있지 않으면 로그인 페이지로 리다이렉트
        if (email == null || email.isEmpty()) {
            response.sendRedirect("/login");
            return false; // 요청 처리 중단
        }

        return true; // 요청 계속 진행
    }
}
