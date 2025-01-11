package ua.study.one_to_many.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    
    @NotBlank(message = "The book should have name")
    @Size(min = 1, max = 100, message = "The book name should have length 1-100 symbols")
    String title;

    @NotBlank(message = "The book should have name")
    @Size(max = 100, message = "The book name should have length 1-100 symbols")
    String author;

    @Digits(integer = 4, fraction = 0, message = "Publication year must be a 4-digit number")
    int pubYear;

    @Min(value = 1, message = "Inventory number should be unique and more than zero")
    Integer invNumber;

}
