package com.niit.ApiGateWay.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p
                        .path("/user/v1/**")
                        .uri("http://localhost:7031/")
                ).route(p->p
                        .path("/movie/api/**")
                        .uri("http://localhost:7061/")
                )
                .route(p->p
                        .path("/api/v1/**")
                        .uri("http://localhost:7071/")
                )
                .route(p->p
                        .path("/favourite/**")
                        .uri("http://localhost:8081/")
                ).route(p->p
                .path("/notification/**")
                .uri("http://localhost:7041/")
                ).route(p->p
                        .path("/api/filter/**")
                        .uri("http://localhost:8082/")
                ).build();
    }
}
