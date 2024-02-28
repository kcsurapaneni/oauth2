-- registering a client in oauth2_registered_client table

-- client_secret = password
INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret,
                                      client_secret_expires_at, client_name, client_authentication_methods,
                                      authorization_grant_types, redirect_uris, post_logout_redirect_uris,
                                      scopes, client_settings, token_settings)
VALUES ('8ddbaed7-d5be-11ee-9758-0242ac130002', 'spotify', CURRENT_TIMESTAMP,
        '{bcrypt}$2a$12$FSqte2Xlpn1B3C1wldykTuPzVFsvMgfroMaKlR.dJesbEdykhi0ia', null,
        'Spotify', 'client_secret_basic',
        'refresh_token,client_credentials,authorization_code', 'http://127.0.0.1:8082/login/oauth2/code/spotify', '',
        'openid,admin',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');

-- with Proof Key for Code Exchange (PKCE)

INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret,
                                      client_secret_expires_at, client_name, client_authentication_methods,
                                      authorization_grant_types, redirect_uris, post_logout_redirect_uris,
                                      scopes, client_settings, token_settings)
VALUES ('8ddbf809-d5be-11ee-9758-0242ac130002', 'mobile', CURRENT_TIMESTAMP,
        null, null,
        'mobile-native-app', 'none',
        'authorization_code', 'http://127.0.0.1:8088', '',
        'openid,admin',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":true,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');

-- client_secret = secret

INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret,
                                      client_secret_expires_at, client_name, client_authentication_methods,
                                      authorization_grant_types, redirect_uris, post_logout_redirect_uris,
                                      scopes, client_settings, token_settings)
VALUES ('8ddc40d8-d5be-11ee-9758-0242ac130002', 'slack', CURRENT_TIMESTAMP,
        '{bcrypt}$2a$12$mckf2mnaSXYo0SVASmjul.Zv4fEfLpOXbXtIL.N2PpuhCA1nlRQSK', null,
        'Slack', 'client_secret_basic',
        'refresh_token,client_credentials,authorization_code', 'http://127.0.0.1:8082/login/oauth2/code/slack', '',
        'openid,admin',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');
