-- DROP

drop table if exists user_roles cascade;
drop table if exists user cascade;
drop table if exists role cascade;

drop table if exists oauth2_registered_client cascade;

-- CREATE

-- user

create table user (
    is_active boolean default true not null,
    created_at timestamp default current_timestamp not null,
    id bigint not null,
    address varchar(255) not null,
    name varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null unique,
    primary key (id)
);


-- role

create table role (
    is_active boolean default true not null,
    id bigint not null,
    name varchar(255) unique,
    primary key (id)
);


-- user_roles

create table user_role (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);


-- oauth2_registered_client

CREATE TABLE oauth2_registered_client (
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(200) DEFAULT NULL,
    client_secret_expires_at timestamp DEFAULT NULL,
    client_name varchar(200) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(1000) NOT NULL,
    redirect_uris varchar(1000) DEFAULT NULL,
    post_logout_redirect_uris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);
