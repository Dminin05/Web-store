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
values ('user', '$2y$10$D7wpehwGf4avzyy6.pwZiuzcHyTkM9U1br2ZH.SXVwZavuJhvhtyO', 'usertest@gmail.com'),
       ('admin', '$2y$10$6fXxe//uQw5raumo5tnqpOEDcLloQx62kjP/XLHRMSNGsseVsQEby', 'admintest@gmail.com');

insert into users_roles (user_id, role_id)
values (1,1), (2, 2);



create table categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values ('Food'),
       ('Electronic');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)
);
insert into products (title, price, category_id)
values ('Milk', 70, 1),
       ('Meat', 300, 1),
       ('Tomato', 220, 1),
       ('Pasta', 140, 1),
       ('Water', 40, 1),
       ('Nuts', 300, 1),
       ('Apple', 100, 1),
       ('Phone', 4000, 2),
       ('Ipad', 5000, 2),
       ('Playstation', 8000, 2),
       ('Computer', 10000, 2),
       ('Camera', 4500, 2),
       ('Watch', 3200, 2);
