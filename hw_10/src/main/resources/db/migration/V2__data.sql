insert into hw_10.authors (id, full_name)
values (1, 'Author_1'),
       (2, 'Author_2'),
       (3, 'Author_3');

insert into hw_10.genres (id, name)
values (1, 'Genre_1'),
       (2, 'Genre_2'),
       (3, 'Genre_3');

insert into hw_10.books (id, title, author_id, genre_id)
values (1, 'BookTitle_1', 1, 1),
       (2, 'BookTitle_2', 2, 2),
       (3, 'BookTitle_3', 3, 3);

insert into hw_10.comments (id, comment)
values (1, 'comment_1'),
       (2, 'comment_2'),
       (3, 'comment_3');