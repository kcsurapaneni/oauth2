-- DROP

drop table if exists user_roles cascade;
drop table if exists user cascade;
drop table if exists role cascade;

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
