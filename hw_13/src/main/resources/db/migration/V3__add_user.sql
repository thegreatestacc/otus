create table if not exists hw_13.users
(
    id       bigint primary key,
    user_name varchar(255),
    password varchar(255)
);

insert into hw_13.users (id, user_name, password)
values (1, 'user', 'usr'),
       (2, 'admin', 'pwd'),
       (3, 'harry', 'potter');