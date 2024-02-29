create table if not exists hw_12.users
(
    id       bigint primary key,
    username varchar(255),
    password varchar(255)
);

insert into hw_12.users (id, username, password)
values (1, 'user', 'usr'),
       (2, 'admin', 'pwd');