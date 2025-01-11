package ua.study.one_to_many.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ua.study.one_to_many.model.Book;
import ua.study.one_to_many.service.impl.BookService;
import ua.study.one_to_many.util.BookValidator;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final BookValidator bookValidator;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "book/all";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "book/book";
    }

    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "book/new";
        }
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/edit/{id}")
    public String editBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
    @ModelAttribute("book") @Valid Book book,
    BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.update(id, book);
        return "redirect:/book" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

}
