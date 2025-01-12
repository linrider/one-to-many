package ua.study.one_to_many.model;

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
public class Person {
    int id;
    
    @NotBlank(message = "First name should be present")
    @Size(min = 2, max = 20, message = "First name length should be between 2-20")
    String firstName;
    
    @NotBlank(message = "Last name should be present")
    @Size(min = 2, max = 20, message = "Last name length should be between 2-20")
    String lastName;

    @Min(value = 1901, message = "Birth year should be not less than 1900")
    int birthYear;

    @NotBlank(message = "Passport number should be present")
    @Size(min = 8, max = 8, message = "Passport number length should be 8")
    String passportNr;
    

}
