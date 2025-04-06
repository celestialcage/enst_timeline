package com.shabet.ensthistory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")  // common 때문에...
                .allowedHeaders("*")
                .allowedMethods("GET", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600);

        registry.addMapping("/api/system-admin/**")
                .allowedOrigins("http://localhost:5173")  // 관리자
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600);

        registry.addMapping("/api/user/**")
                .allowedOrigins("http://localhost:3000")  // 사용자
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
