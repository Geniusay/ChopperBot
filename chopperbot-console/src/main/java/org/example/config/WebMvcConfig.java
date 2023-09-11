package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Genius
 * @date 2023/09/11 00:41
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiPrefixInterceptor());
    }

    @Bean
    public ApiPrefixInterceptor apiPrefixInterceptor() {
        return new ApiPrefixInterceptor();
    }
}
