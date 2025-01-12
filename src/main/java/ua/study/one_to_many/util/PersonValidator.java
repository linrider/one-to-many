package ua.study.one_to_many.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;
import ua.study.one_to_many.model.Person;
import ua.study.one_to_many.service.impl.PersonService;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {
    private final PersonService personService;

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (person.getId() != 0) {
            Person previosVersion = personService.getById(person.getId());
            if (!previosVersion.getPassportNr().equals(person.getPassportNr())
                    && personService.findByPassport(person.getPassportNr()).isPresent()) {
                errors.rejectValue("passportNr", "", "This passport is allready registered");
            }
        }
    }

}
