package com.learn.client.config;

import org.springframework.cloud.gateway.route.*;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    RouteLocator gateway(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route(route -> route.path("/hello")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("http://localhost:7070/hello"))
                .build();
    }

}
