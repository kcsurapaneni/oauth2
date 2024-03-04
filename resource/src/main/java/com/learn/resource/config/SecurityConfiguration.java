package com.learn.resource.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtDecoder jwtDecoder) throws Exception {
        httpSecurity
                .authorizeHttpRequests(httpRequests ->
                        httpRequests.anyRequest().authenticated()
                )
                .oauth2ResourceServer(resourceServer ->
                        resourceServer.jwt(jwt ->
                                jwt.decoder(jwtDecoder)
                        )
                );
        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("http://localhost:9090");
    }

}
