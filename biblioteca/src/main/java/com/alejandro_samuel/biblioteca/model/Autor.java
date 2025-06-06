package com.alejandro_samuel.biblioteca.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String nacionalidad;
    
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {
    }
    
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNacionalidad() {
        return nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }
    
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    
    // Métodos helper para mantener la sincronización de la relación bidireccional
    public void addLibro(Libro libro) {
        libros.add(libro);
        libro.setAutor(this);
    }
    
    public void removeLibro(Libro libro) {
        libros.remove(libro);
        libro.setAutor(null);
    }
}