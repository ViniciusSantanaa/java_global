package com.livros.catalogo_livros.repository;

import com.livros.catalogo_livros.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository 
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    Page<Book> findByAnoPublicacaoBetween(Integer startYear, Integer endYear, Pageable pageable);
}