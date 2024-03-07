INSERT INTO oauth2_registered_client
(id, client_id, client_secret, client_name, client_authentication_method, authorization_grant_type, authorization_uri, token_uri,
 redirect_uri, scopes, issuer_uri, jwk_set_uri)
VALUES ( 'spotify', 'spotify', 'password', 'Spotify', 'client_secret_post', 'authorization_code'
       , 'http://localhost:9090/oauth2/authorize', 'http://localhost:9090/oauth2/token'
       , '{baseUrl}/login/oauth2/code/{registrationId}', 'openid,admin'
       , 'http://localhost:9090', 'http://localhost:9090/oauth2/jwks');

INSERT INTO oauth2_registered_client
(id, client_id, client_secret, client_name, client_authentication_method, authorization_grant_type, authorization_uri, token_uri,
 redirect_uri, scopes, issuer_uri, jwk_set_uri)
VALUES ( 'slack', 'slack', 'secret', 'Slack', 'client_secret_basic', 'authorization_code'
       , 'http://localhost:9090/oauth2/authorize', 'http://localhost:9090/oauth2/token'
       , '{baseUrl}/login/oauth2/code/{registrationId}', 'openid,admin'
       , 'http://localhost:9090', 'http://localhost:9090/oauth2/jwks');
