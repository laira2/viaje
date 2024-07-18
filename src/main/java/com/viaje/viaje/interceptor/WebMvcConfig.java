//package com.viaje.viaje.interceptor;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//@RequiredArgsConstructor
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // AuthInterceptor를 등록하여 모든 요청에 인터셉트하도록 설정
//        registry.addInterceptor(new AuthInterceptor())
//                .addPathPatterns("/**") // 모든 요청에 대해 인터셉트
//                .excludePathPatterns("/","/loginPage","/join", "/login", "/logout","/yac","/css/**","/js/**","/templates/**","/static/**"); // 인증 체크 제외할 URL 패턴 설정
//    }
//}
