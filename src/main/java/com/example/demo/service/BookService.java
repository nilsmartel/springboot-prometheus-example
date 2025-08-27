package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }
    
    public Book create(Book book) {
        book.setId(null); // Stelle sicher, dass eine neue ID generiert wird
        return bookRepository.save(book);
    }
    
    public Book update(Long id, Book bookDetails) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setName(bookDetails.getName());
            book.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(book);
        }
        return null;
    }
    
    public boolean delete(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
