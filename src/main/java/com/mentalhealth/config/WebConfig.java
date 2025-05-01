package com.mentalhealth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // More specific than "/**"
                .allowedOrigins("http://localhost:63343")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add OPTIONS!
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);  // Cache preflight response for 1 hour
    }

}
