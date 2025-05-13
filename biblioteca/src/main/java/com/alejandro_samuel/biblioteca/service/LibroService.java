package com.alejandro_samuel.biblioteca.service;

import com.alejandro_samuel.biblioteca.model.Libro;
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
    
    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    
    public List<Libro> obtenerTodosLibros() {
        return libroRepository.findAll();
    }
    
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }
    
    @Transactional
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }
    
    @Transactional
    public Optional<Libro> actualizarLibro(Long id, Libro libroActualizado) {
        return libroRepository.findById(id)
                .map(libroExistente -> {
                    libroExistente.setTitulo(libroActualizado.getTitulo());
                    libroExistente.setIsbn(libroActualizado.getIsbn());
                    libroExistente.setAnioPublicacion(libroActualizado.getAnioPublicacion());
                    libroExistente.setAutor(libroActualizado.getAutor());
                    return libroRepository.save(libroExistente);
                });
    }
    
    @Transactional
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
    
    public List<Libro> buscarLibros(String titulo, Integer anio, String sortBy, String order) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        return libroRepository.buscarLibros(titulo, anio, sort);
    }
}