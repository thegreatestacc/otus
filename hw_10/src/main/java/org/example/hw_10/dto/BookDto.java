package org.example.hw_10.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.hw_10.models.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {
    @NotNull
    Long bookId;
    @NotEmpty
    String title;
    @NotNull
    Long authorId;
    @NotNull
    Long genreId;
}
