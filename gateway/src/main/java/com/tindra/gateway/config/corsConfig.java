package com.tindra.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;

@Configuration
public class corsConfig {

    /**
     * 配置跨域过滤器
     *
     * @return 跨域过滤器实例
     */
    @Bean
    public CorsWebFilter corsConfiguration() {
        // 创建跨域配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有请求方法
        corsConfiguration.addAllowedMethod("*");
        // 允许发送 Cookie
        corsConfiguration.setAllowCredentials(true);
        // 允许所有来源
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        // 允许所有请求头
        corsConfiguration.addAllowedHeader("*");
        // 创建基于 URL 的跨域配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        // 注册跨域配置
        source.registerCorsConfiguration("/**", corsConfiguration);
        // 创建跨域过滤器并返回
        return new CorsWebFilter(source);
    }

}
