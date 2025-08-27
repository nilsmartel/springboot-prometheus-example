package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    
    private final BookService bookService;
    
    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Optional<Book> book = bookService.getById(id);
        return book.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book createdBook = bookService.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.update(id, bookDetails);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = bookService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
