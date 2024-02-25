package com.learn.authorization.config;

import org.springframework.boot.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.oidc.*;
import org.springframework.security.oauth2.server.authorization.client.*;
import org.springframework.security.oauth2.server.authorization.settings.*;

import java.util.*;

/**
 * @author Krishna Chaitanya
 */
@Configuration
public class ClientConfiguration {

    @Bean
    RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

//    @Bean
    ApplicationRunner clientRunner(RegisteredClientRepository clientRepository) {
        return args -> {
            var clientId = "photo";
            if (Objects.isNull(clientRepository.findByClientId(clientId))) {
                clientRepository.save(
                        RegisteredClient
                                .withId(UUID.randomUUID().toString())
                                .clientId(clientId)
                                .clientSecret("{bcrypt}$2a$12$FSqte2Xlpn1B3C1wldykTuPzVFsvMgfroMaKlR.dJesbEdykhi0ia")
                                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                                .authorizationGrantTypes(authorizationGrantTypes -> authorizationGrantTypes.addAll(Set.of(
                                        AuthorizationGrantType.AUTHORIZATION_CODE,
                                        AuthorizationGrantType.CLIENT_CREDENTIALS,
                                        AuthorizationGrantType.REFRESH_TOKEN
                                )))
                                .clientSettings(
                                        ClientSettings
                                                .builder()
                                                .requireAuthorizationConsent(true)
                                                .build()
                                )
                                .redirectUri("http://127.0.0.1:8082/login/oauth2/code/hello")
                                .scopes(scopes -> scopes.addAll(Set.of(
                                        OidcScopes.OPENID,
                                        "admin"
                                )))
                                .build()
                );
            }
        };
    }

}
