package org.example.hw_8.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.example.hw_8.models.Author;
import org.example.hw_8.models.Book;
import org.example.hw_8.models.Comment;
import org.example.hw_8.models.Genre;
import org.example.hw_8.repositories.AuthorRepository;
import org.example.hw_8.repositories.BookRepository;
import org.example.hw_8.repositories.CommentRepository;
import org.example.hw_8.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "seedAuthor", author = "me")
    public void setAuthor(AuthorRepository authorRepository) {
        List<Author> authors = new ArrayList<>();

        authors.add(new Author(UUID.randomUUID().toString(), "full_name_1"));
        authors.add(new Author(UUID.randomUUID().toString(), "full_name_2"));
        authors.add(new Author(UUID.randomUUID().toString(), "full_name_3"));

        authorRepository.insert(authors);
    }

    @ChangeSet(order = "002", id = "seedComment", author = "me")
    public void setComment(CommentRepository commentRepository) {
        List<Comment> comments = new ArrayList<>();

        comments.add(new Comment(UUID.randomUUID().toString(), "comment_1", new Book()));
        comments.add(new Comment(UUID.randomUUID().toString(), "comment_2", new Book()));
        comments.add(new Comment(UUID.randomUUID().toString(), "comment_3", new Book()));

        commentRepository.insert(comments);
    }

    @ChangeSet(order = "003", id = "seedBook", author = "me")
    public void setBook(BookRepository bookRepository) {
        List<Book> books = new ArrayList<>();

        books.add(new Book(UUID.randomUUID().toString(), "title_1", new Author(), new Genre()));
        books.add(new Book(UUID.randomUUID().toString(), "title_2", new Author(), new Genre()));
        books.add(new Book(UUID.randomUUID().toString(), "title_3", new Author(), new Genre()));

        bookRepository.insert(books);
    }

    @ChangeSet(order = "004", id = "seedGenre", author = "me")
    public void setGenres(GenreRepository genreRepository) {
        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre(UUID.randomUUID().toString(), "genre_1"));
        genres.add(new Genre(UUID.randomUUID().toString(), "genre_2"));
        genres.add(new Genre(UUID.randomUUID().toString(), "genre_3"));

        genreRepository.insert(genres);
    }
}
