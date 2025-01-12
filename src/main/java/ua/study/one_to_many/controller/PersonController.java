package ua.study.one_to_many.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ua.study.one_to_many.model.Person;
import ua.study.one_to_many.service.impl.BookService;
import ua.study.one_to_many.service.impl.PersonService;
import ua.study.one_to_many.util.PersonValidator;

@RequiredArgsConstructor
@Controller
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;
    private final BookService bookService;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personService.getAll());
        return "person/all";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        model.addAttribute("books", bookService.getAllBooksByPersonId(id));
        return "person/person";
    }

    @GetMapping("/new")
    public String newPersonPage(@ModelAttribute("person") Person person) {
        return "person/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") @Valid Person person,
            BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person/new";
        }
        personService.save(person);
        return "redirect:/person";
    }

    @GetMapping("/edit/{id}")
    public String editUserPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
            @ModelAttribute("user") @Valid Person person,
            BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person/edit";
        }
        personService.update(id, person);
        return "redirect:/person/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.deleteById(id);
        return "redirect:/person";
    }
}
