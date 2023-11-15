create table if not exists authors
(
    id        bigserial not null,
    full_name varchar(255),
    primary key (id)
);

create table if not exists genres
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);

create table if not exists books
(
    id        bigserial                                        not null,
    title     varchar(255),
    author_id bigint references authors (id) on delete cascade not null,
    genre_id  bigint references genres (id) on delete cascade  not null,
    primary key (id)
);