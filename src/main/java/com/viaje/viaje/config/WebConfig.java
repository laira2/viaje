package com.viaje.viaje.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${file.upload-dir.plan}")
    private String planUploadDir;

    @Value("${file.upload-dir.cert}")
    private String certUploadDir;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 기존 코드
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // 새로 추가된 코드
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600)
                .resourceChain(true);

        // 파일 업로드 디렉토리 설정
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + planUploadDir + "/")
                .setCachePeriod(3600)
                .resourceChain(true);

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + certUploadDir + "/")
                .setCachePeriod(3600)
                .resourceChain(true);


    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}