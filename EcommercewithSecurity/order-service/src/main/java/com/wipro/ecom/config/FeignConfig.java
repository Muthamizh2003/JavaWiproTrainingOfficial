package com.wipro.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {

            ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attrs != null) {
                String token = attrs.getRequest().getHeader("Authorization");

                if (token != null && token.startsWith("Bearer ")) {
                    requestTemplate.header("Authorization", token);
                    System.out.println("Forwarding token: " + token);
                }
            } else {
                System.out.println("RequestContextHolder is NULL ❌");
            }
        };
    }
}