package com.learn.authorization.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
public class OAuth2AuthorizationConfiguration {

    @Bean
    OAuth2AuthorizationService oAuth2AuthorizationService(JdbcTemplate jdbcTemplate,
                                                          RegisteredClientRepository registeredClientRepository) {
        // https://github.com/spring-projects/spring-authorization-server/issues/397#issuecomment-900148920
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

}
