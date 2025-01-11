package ua.study.one_to_many.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.study.one_to_many.model.Book;
import ua.study.one_to_many.service.CrudService;

@Service
@RequiredArgsConstructor
public class BookService implements CrudService<Book, Integer> {
    private final JdbcTemplate jdbcTemplate;

    @SuppressWarnings("deprecation")
    public Optional<Book> findByInvNumber(int invNumber) {
        return jdbcTemplate.query("SELECT * FROM book WHERE inv_nr=?", new Object[] {invNumber},
        new BeanPropertyRowMapper<>(Book.class))
        .stream()
        .findAny();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper(Book.class));
    }

    @SuppressWarnings("deprecation")
    @Override
    public Book getById(Integer id) {
        return jdbcTemplate
                .query("SELECT * FROM book WHERE id=?", new Object[] { id },
                        new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElseThrow();
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, author, pub_year, inv_nr) VALUES(?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPubYear(), book.getInvNumber());
    }

    @Override
    public void update(Integer id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, pub_year=?, inv_nr=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getPubYear(), book.getInvNumber(), id);
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

}
