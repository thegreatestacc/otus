create table if not exists authors
(
    id        int generated by default as identity (start with 1) primary key,
    full_name varchar(255),
    primary key (id)
);

create table if not exists genres
(
    id   int generated by default as identity (start with 1) primary key,
    name varchar(255),
    primary key (id)
);

create table if not exists comments
(
    id      int generated by default as identity (start with 1) primary key,
    comment varchar(255),
    primary key (id)
);

create table if not exists books
(
    id         int generated by default as identity (start with 1) primary key,
    title      varchar(255),
    author_id  bigint references authors (id) on delete cascade,
    genre_id   bigint references genres (id) on delete cascade,
    comment_id bigint references comments (id) on delete cascade,
    primary key (id)
);