package com.kc.te.config;

import com.kc.te.service.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.function.*;

import static com.kc.te.config.RequestPredicates.*;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.*;


/**
 * @author Krishna Chaitanya
 */
@Configuration
class RouteConfiguration {


    private final JwtService jwtService;

    RouteConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Bean
    public RouterFunction<ServerResponse> gatewayRouterFunctionsAddReqHeader() {
        return RouterFunctions.route()
                .route(apiCheck(), http("http://localhost:7272/"))
                .filter(JwtScopesTokenFilterFunctions.token(jwtService))
                .build();
    }

}
