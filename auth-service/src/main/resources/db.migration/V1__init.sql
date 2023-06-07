create table users
(
    id bigserial primary key,
    username varchar(30) not null unique,
    password varchar(80) not null unique,
    email varchar(30) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id bigserial primary key,
    name varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id bigint not null,
    role_id int not null,
    primary key(user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2y$10$D7wpehwGf4avzyy6.pwZiuzcHyTkM9U1br2ZH.SXVwZavuJhvhtyO', 'usertest@gmail.com'), /*password = user*/
       ('admin', '$2y$10$6fXxe//uQw5raumo5tnqpOEDcLloQx62kjP/XLHRMSNGsseVsQEby', 'admintest@gmail.com');/*password = admin*/

insert into users_roles (user_id, role_id)
values (1,1), (2, 2);