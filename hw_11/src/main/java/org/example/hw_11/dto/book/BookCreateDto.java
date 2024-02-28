package org.example.hw_11.dto.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
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
