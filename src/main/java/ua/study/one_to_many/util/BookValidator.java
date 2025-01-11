package ua.study.one_to_many.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;
import ua.study.one_to_many.model.Book;
import ua.study.one_to_many.service.impl.BookService;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator{
    private final BookService bookService;

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (book.getId() != 0) {
            Book previousVersion = bookService.getById(book.getId());

            if (!previousVersion.getInvNumber().equals(book.getInvNumber())
            && bookService.findByInvNumber(book.getInvNumber()).isPresent()) {
                errors.rejectValue("invNumber", "", "The book with such inventory number already exists");
            }
        }
    }


}
