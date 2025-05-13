package com.alejandro_samuel.biblioteca.service;

import com.alejandro_samuel.biblioteca.model.Autor;
import com.alejandro_samuel.biblioteca.model.Libro;
import com.alejandro_samuel.biblioteca.repository.AutorRepository;
import com.alejandro_samuel.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    
    @Autowired
    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }
    
    public List<Libro> obtenerTodosLibros() {
        return libroRepository.findAll();
    }
    
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }
    
    @Transactional
    public Libro guardarLibro(Libro libro) {
        // Si el libro tiene un autor con ID, necesitamos vincularlo al autor existente
        if (libro.getAutor() != null && libro.getAutor().getId() != null) {
            Autor autor = autorRepository.findById(libro.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + libro.getAutor().getId()));
            
            // Establecer la relación bidireccional
            libro.setAutor(autor);
            autor.addLibro(libro);
            
            // Solo guardar el libro, el autor se actualiza por cascada
            return libroRepository.save(libro);
        }
        
        return libroRepository.save(libro);
    }
    
    @Transactional
    public Optional<Libro> actualizarLibro(Long id, Libro libroActualizado) {
        return libroRepository.findById(id)
                .map(libroExistente -> {
                    // Preservamos el autor existente si no se proporciona uno nuevo
                    Autor autorActual = libroExistente.getAutor();
                    Autor autorNuevo = libroActualizado.getAutor();
                    
                    // Actualizamos datos básicos
                    libroExistente.setTitulo(libroActualizado.getTitulo());
                    libroExistente.setIsbn(libroActualizado.getIsbn());
                    libroExistente.setAnioPublicacion(libroActualizado.getAnioPublicacion());
                    
                    // Manejo de la relación con autor
                    if (autorNuevo != null && autorNuevo.getId() != null) {
                        // Si hay un nuevo autor, lo recuperamos de la base de datos
                        Autor autor = autorRepository.findById(autorNuevo.getId())
                            .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + autorNuevo.getId()));
                        
                        // Si el libro tenía un autor diferente antes, eliminamos la relación
                        if (autorActual != null && !autorActual.getId().equals(autor.getId())) {
                            autorActual.removeLibro(libroExistente);
                        }
                        
                        // Establecemos el nuevo autor
                        autor.addLibro(libroExistente);
                    } else if (autorNuevo == null && autorActual != null) {
                        // Si se eliminó el autor, quitamos la relación
                        autorActual.removeLibro(libroExistente);
                    }
                    
                    return libroRepository.save(libroExistente);
                });
    }
    
    @Transactional
    public void eliminarLibro(Long id) {
        libroRepository.findById(id).ifPresent(libro -> {
            // Eliminamos la relación con el autor antes de eliminar el libro
            if (libro.getAutor() != null) {
                libro.getAutor().removeLibro(libro);
            }
            libroRepository.delete(libro);
        });
    }
    
    public List<Libro> buscarLibros(String titulo, Integer anio, String sortBy, String order) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        return libroRepository.buscarLibros(titulo, anio, sort);
    }
}