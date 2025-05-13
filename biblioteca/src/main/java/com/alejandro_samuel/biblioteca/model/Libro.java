package com.alejandro_samuel.biblioteca.model;

import jakarta.persistence.*;


@Entity
@Table(name = "libros")
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    
    private String isbn;
    
    private int anioPublicacion;


    
    public Libro(Long id, String titulo, String isbn, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
    }

    Libro(){

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }



    @ManyToOne
    private Autor autor;



    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    

}