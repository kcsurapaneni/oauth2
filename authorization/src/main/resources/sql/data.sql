-- DELETE scripts

DELETE FROM oauth2_registered_client;
DELETE FROM user_role;
DELETE FROM role;
DELETE FROM user;


-- registering a client in oauth2_registered_client table
-- client_secret = secret
INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret,
                                      client_secret_expires_at, client_name, client_authentication_methods,
                                      authorization_grant_types, redirect_uris, post_logout_redirect_uris,
                                      scopes, client_settings, token_settings)
VALUES (UUID(), 'photo', CURRENT_TIMESTAMP,
        '{bcrypt}$2a$12$FSqte2Xlpn1B3C1wldykTuPzVFsvMgfroMaKlR.dJesbEdykhi0ia', null,
        'photo-sharing-app', 'client_secret_basic',
        'refresh_token,client_credentials,authorization_code', 'http://127.0.0.1:8082/login/oauth2/code/hello', '',
        'openid,admin',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');


-- role table data

INSERT INTO role
    (id, name)
VALUES (1, 'ROLE_read'),
       (2, 'ROLE_editor'),
       (3, 'ROLE_admin');


-- client table data
-- password = secured

INSERT INTO user
    (id, name, username, password, address)
VALUES (1, 'Krishna Chaitanya', 'kcsurapaneni', '$2a$10$Gnuih1/NZ36TXu.K3GEc.eseoIVxBgZyd0Xx5rbdP7.c18WwcPbDS', 'India'),
       (2, 'Vamsi Krishna', 'vkkolluri', '$2a$10$Gnuih1/NZ36TXu.K3GEc.eseoIVxBgZyd0Xx5rbdP7.c18WwcPbDS', 'USA'),
       (3, 'Ramya Krishna', 'rkbodepudi', '$2a$10$Gnuih1/NZ36TXu.K3GEc.eseoIVxBgZyd0Xx5rbdP7.c18WwcPbDS', 'Canada');


-- client_roles

INSERT INTO user_role
    (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 2),
       (3, 3);
