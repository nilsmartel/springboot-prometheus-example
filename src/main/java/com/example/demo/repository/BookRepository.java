package com.example.demo.repository;

import com.example.demo.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
    boolean existsById(Long id);
}
