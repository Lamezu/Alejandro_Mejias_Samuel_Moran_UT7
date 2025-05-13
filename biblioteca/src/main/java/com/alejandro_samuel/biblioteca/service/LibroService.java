package com.alejandro_samuel.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alejandro_samuel.biblioteca.model.Libro;
import com.alejandro_samuel.biblioteca.repository.LibroRepository;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;
    
    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    
    public List<Libro> buscarLibros(String titulo, Integer anioPublicacion, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        
        // Si usas la opción con @Query
        return libroRepository.buscarLibros(titulo, anioPublicacion, sort);
        
        // Si usas los métodos de Spring Data
        // if (titulo != null && anioPublicacion != null) {
        //     return libroRepository.findByTituloContainingAndAnioPublicacion(titulo, anioPublicacion, sort);
        // } else if (titulo != null) {
        //     return libroRepository.findByTituloContaining(titulo, sort);
        // } else if (anioPublicacion != null) {
        //     return libroRepository.findByAnioPublicacion(anioPublicacion, sort);
        // } else {
        //     return libroRepository.findAll(sort);
        // }
    }
    
    // Otros métodos del servicio...
}