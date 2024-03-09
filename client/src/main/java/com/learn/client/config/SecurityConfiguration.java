package com.learn.client.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.web.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(requests ->
                        requests.anyRequest().authenticated()
                )
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    OAuth2AuthorizedClientService reactiveOAuth2AuthorizedClientService(JdbcTemplate jdbcTemplate, ClientRegistrationRepository clientRegistrationRepository) {
        return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository);
    }

}
