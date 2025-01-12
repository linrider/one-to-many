package ua.study.one_to_many.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    int id;
    
    @NotBlank(message = "The book should have a name")
    @Size(min = 1, max = 100, message = "The book name should have length 1-100 symbols")
    String title;

    @NotBlank(message = "The book should have author name")
    @Size(max = 100, message = "The book name should have length 1-100 symbols")
    String author;

    @Min(value = 1000, message = "Publication year must be at least 1000")
    @Max(value = 2025, message = "Publication year cannot be in the future")
    @NotNull(message = "Publication date must not be null")
    Integer pubYear;

    @NotNull(message = "Inventory number must not be null")
    @Min(value = 1, message = "Inventory number should be unique and more than zero")
    Integer invNr;
}
