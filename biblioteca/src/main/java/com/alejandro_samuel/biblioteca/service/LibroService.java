package com.alejandro_samuel.biblioteca.service;

import com.alejandro_samuel.biblioteca.model.Libro;
import com.alejandro_samuel.biblioteca.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // Obtener todos los libros
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    // Obtener libro por ID
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    // Guardar libro
    @Transactional
    public Libro guardarLibro(Libro libro) {
        // Si se proporciona un autor por ID, lo enlazamos
        if (libro.getAutor() != null && libro.getAutor().getId() != null) {
            autorRepository.findById(libro.getAutor().getId())
                    .ifPresent(libro::setAutor);
        }
        return libroRepository.save(libro);
    }

    // Eliminar libro
    @Transactional
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    // Actualizar libro
    @Transactional
    public Optional<Libro> actualizarLibro(Long id, Libro libroActualizado) {
        return libroRepository.findById(id)
                .map(libroExistente -> {
                    libroExistente.setTitulo(libroActualizado.getTitulo());
                    libroExistente.setIsbn(libroActualizado.getIsbn());
                    libroExistente.setAnioPublicacion(libroActualizado.getAnioPublicacion());
                    
                    // Actualizamos el autor solo si se proporciona uno nuevo
                    if (libroActualizado.getAutor() != null && libroActualizado.getAutor().getId() != null) {
                        autorRepository.findById(libroActualizado.getAutor().getId())
                                .ifPresent(libroExistente::setAutor);
                    }
                    
                    return libroRepository.save(libroExistente);
                });
    }


    // Buscar libros con filtros y ordenación
    public List<Libro> buscarLibros(String titulo, Integer anio, String sortBy, String order) {
        // Validamos y asignamos valores por defecto
        String campoOrdenacion = (sortBy != null && !sortBy.isEmpty()) ? sortBy : "id";
        Sort.Direction direccion = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direccion, campoOrdenacion);
        
        // Realizamos la búsqueda con los parámetros proporcionados
        return libroRepository.buscarLibros(titulo, anio, sort);
    }
}