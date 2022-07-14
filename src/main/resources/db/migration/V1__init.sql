create table users
(
    id       bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table privileges
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_privileges
(
    user_id bigint not null,
    privilege_id int    not null,
    primary key (user_id, privilege_id),
    foreign key (user_id) references users (id),
    foreign key (privilege_id) references privileges (id)
);

insert into privileges (name)
values ('WRITE_PRIVILEGE'),
       ('READ_PRIVILEGE');

insert into users (username, password, email)
values
    ('user', '$2a$12$ExKvnNJRrWHUhmilrWPQ0eBP1IqYPlaq1AiX0607LnOAFkPh2jKI6', 'user@gmail.com'),
    ('user2', '$2a$12$ExKvnNJRrWHUhmilrWPQ0eBP1IqYPlaq1AiX0607LnOAFkPh2jKI6', 'user2@gmail.com');

insert into users_privileges (user_id, privilege_id)
values (1, 1),
       (1,2),
       (2,2);