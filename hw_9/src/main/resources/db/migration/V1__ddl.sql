create table if not exists hw_9.authors
(
    id        bigint primary key,
    full_name varchar(255)
);

create table if not exists hw_9.genres
(
    id   bigint primary key,
    name varchar(255)
);

create table if not exists hw_9.books
(
    id        bigint primary key,
    title     varchar(255),
    author_id bigint references authors (id),
    genre_id  bigint references genres (id)
);

create table if not exists hw_9.comments
(
    id      bigint primary key,
    comment varchar(255),
    book_id bigint references books (id)
);