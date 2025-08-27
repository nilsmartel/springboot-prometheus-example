package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryBookRepository implements BookRepository {
    
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public InMemoryBookRepository() {
        // Füge einige Beispielücher hinzu
        books.add(new Book(idGenerator.getAndIncrement(), "Clean Code", "Robert C. Martin"));
        books.add(new Book(idGenerator.getAndIncrement(), "Spring in Action", "Craig Walls"));
        books.add(new Book(idGenerator.getAndIncrement(), "Effective Java", "Joshua Bloch"));
    }
    
    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }
    
    @Override
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
    
    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
            books.add(book);
        } else {
            Optional<Book> existingBook = findById(book.getId());
            if (existingBook.isPresent()) {
                int index = books.indexOf(existingBook.get());
                books.set(index, book);
            } else {
                books.add(book);
            }
        }
        return book;
    }
    
    @Override
    public void deleteById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
    
    @Override
    public boolean existsById(Long id) {
        return books.stream()
                .anyMatch(book -> book.getId().equals(id));
    }
}
