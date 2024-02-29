package com.learn.authorization.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
public class ConsentConfiguration {

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
                                                                         RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }

}
