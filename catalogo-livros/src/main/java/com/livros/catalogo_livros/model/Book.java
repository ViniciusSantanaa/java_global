package com.livros.catalogo_livros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity 
public class Book {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotBlank(message = "O título não pode estar em branco") 
    @Size(max = 255, message = "O título não pode ter mais de 255 caracteres") 
    private String titulo;

    @NotBlank(message = "O autor não pode estar em branco")
    @Size(max = 255, message = "O autor não pode ter mais de 255 caracteres")
    private String author;

    @NotNull(message = "O ano de publicação não pode ser nulo")
    @Min(value = 1000, message = "O ano de publicação deve ser um ano válido (ex: 1900)") 
    private Integer anoPublicacao;

    @NotBlank(message = "O ISBN não pode estar em branco")
    @Size(min = 10, max = 13, message = "O ISBN deve ter entre 10 e 13 caracteres") 
    private String isbn;

    public Book() {
    }

    public Book(String titulo, String author, Integer anoPublicacao, String isbn) {
        this.titulo = titulo;
        this.author = author;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}   