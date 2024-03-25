package org.example.hw_13.dto.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookCreateDto {
    @NotNull
    Long bookId;
    @NotEmpty
    String title;
    @NotNull
    Long authorId;
    @NotNull
    Long genreId;
}
