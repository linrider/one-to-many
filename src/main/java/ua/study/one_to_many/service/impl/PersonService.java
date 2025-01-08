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
    public Optional<Person> findByPassport(int passportNumber) {
        return jdbcTemplate.query("SELECT * FROM person WHERE passport_nr = ?", 
        new Object[] {passportNumber}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    @Override
    public List<Person> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Person getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void save(Person t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(Integer id, Person t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
