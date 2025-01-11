package ua.study.one_to_many.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.study.one_to_many.model.Person;
import ua.study.one_to_many.service.CrudService;

@Service
@RequiredArgsConstructor
public class PersonService implements CrudService<Person, Integer> {
    private final JdbcTemplate jdbcTemplate;

    @SuppressWarnings("deprecation")
    public Optional<Person> findByPassport(String passportNumber) {
        return jdbcTemplate.query("SELECT * FROM person WHERE passport_nr = ?",
                new Object[] { passportNumber }, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper(Person.class));
    }

    @Override
    @SuppressWarnings("deprecation")
    public Person getById(Integer id) {
        return jdbcTemplate
                .query("SELECT FROM person WHERE id=?", new Object[] { id },
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElseThrow();
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (first_name, last_name, birth_year, passport_nr) VALUES (?, ?, ?, ?)",
                person.getFirstName(), person.getLastName(), person.getBirthYear(), person.getPassporNr());
    }

    @Override
    public void update(Integer id, Person person) {
        jdbcTemplate.update("UPDATE person SET first_name=?, last_name=?, birth_year=?, passport_nr=? WHERE id=?",
                person.getFirstName(), person.getLastName(), person.getBirthYear(), person.getPassporNr());
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
