package com.learn.client.repository;

import org.springframework.jdbc.core.*;
import org.springframework.security.oauth2.client.registration.*;
import org.springframework.security.oauth2.core.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

import java.sql.*;
import java.util.*;
import java.util.stream.*;

/**
 * @author Krishna Chaitanya
 */
@Repository
public class JdbcReactiveClientRegistrationRepository implements ReactiveClientRegistrationRepository, Iterable<ClientRegistration> {

    private static final String FETCH = """
            SELECT id,
                client_id,
                client_secret,
                client_name,
                client_authentication_method,
                authorization_grant_type,
                authorization_uri,
                token_uri,
                redirect_uri,
                scopes,
                issuer_uri,
                jwk_set_uri
            FROM oauth2_registered_client
            """;
    private static final String FETCH_BY_ID = FETCH + " WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    public JdbcReactiveClientRegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Mono<ClientRegistration> findByRegistrationId(String registrationId) {
        return Mono.justOrEmpty(jdbcTemplate.query(FETCH_BY_ID, this::mapToClient, registrationId).get(0));
    }

    private ClientRegistration mapToClient(ResultSet rs, int rowNum) throws SQLException {
        return ClientRegistration
                .withRegistrationId(rs.getString("id"))
                .clientId(rs.getString("client_id"))
                .clientName(rs.getString("client_name"))
                .clientSecret(rs.getString("client_secret"))
                .clientAuthenticationMethod(
                        new ClientAuthenticationMethod(rs.getString("client_authentication_method"))
                )
                .authorizationGrantType(
                        new AuthorizationGrantType(rs.getString("authorization_grant_type"))
                )
                .authorizationUri(rs.getString("authorization_uri"))
                .tokenUri(rs.getString("token_uri"))
                .redirectUri(rs.getString("redirect_uri"))
                .scope(Arrays.stream(rs.getString("scopes").split(",")).map(String::trim).collect(Collectors.toSet()))
                .issuerUri(rs.getString("issuer_uri"))
                .jwkSetUri(rs.getString("jwk_set_uri"))
                .build();
    }

    List<ClientRegistration> fetchAll() {
        return jdbcTemplate.query(FETCH, this::mapToClient);
    }

    @Override
    public Iterator<ClientRegistration> iterator() {
        return fetchAll().iterator();
    }

}
