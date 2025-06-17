package com.livros.catalogo_livros.controller;

import com.livros.catalogo_livros.model.Book;
import com.livros.catalogo_livros.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController 
@RequestMapping("/livros") 
public class BookController {

    @Autowired 
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book existingBook = book.get();
            existingBook.setTitulo(bookDetails.getTitulo());
            existingBook.setAuthor(bookDetails.getAuthor());
            existingBook.setAnoPublicacao(bookDetails.getAnoPublicacao());
            existingBook.setIsbn(bookDetails.getIsbn());
            Book updatedBook = bookRepository.save(existingBook);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer anoPublicacaoStart,
            @RequestParam(required = false) Integer anoPublicacaoEnd) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books;

        if (author != null && !author.isEmpty()) {
            books = bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
        } else if (titulo != null && !titulo.isEmpty()) {
            books = bookRepository.findByTituloContainingIgnoreCase(titulo, pageable);
        } else if (anoPublicacaoStart != null && anoPublicacaoEnd != null) {
            books = bookRepository.findByAnoPublicacaoBetween(anoPublicacaoStart, anoPublicacaoEnd, pageable);
        } else {
            books = bookRepository.findAll(pageable); 
        }

        return new ResponseEntity<>(books, HttpStatus.OK); 
    }
}