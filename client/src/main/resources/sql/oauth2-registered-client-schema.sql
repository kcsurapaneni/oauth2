CREATE TABLE oauth2_registered_client (
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_secret varchar(200) DEFAULT NULL,
    client_name varchar(200) DEFAULT NULL,
    client_authentication_method varchar(1000) NOT NULL,
    authorization_grant_type varchar(1000) NOT NULL,
    authorization_uri varchar(1000) NOT NULL,
    token_uri varchar(1000) DEFAULT NULL,
    redirect_uri varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    issuer_uri varchar(1000) DEFAULT NULL,
    jwk_set_uri varchar(1000) DEFAULT NULL,
    PRIMARY KEY (id)
);
