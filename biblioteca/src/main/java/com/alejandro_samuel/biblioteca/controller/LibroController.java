package com.alejandro_samuel.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro_samuel.biblioteca.model.Libro;
import com.alejandro_samuel.biblioteca.service.LibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    
    private final LibroService libroService;
    
    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }
    
    @GetMapping("/buscar")
    public List<Libro> buscarLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer anioPublicacion,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        
        return libroService.buscarLibros(titulo, anioPublicacion, sortBy, direction);
    }
    
    // Otros endpoints...
}