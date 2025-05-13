package com.alejandro_samuel.biblioteca.service;

import com.alejandro_samuel.biblioteca.model.Autor;
import com.alejandro_samuel.biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Obtener todos los autores
    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    // Obtener autor por ID
    public Optional<Autor> obtenerAutorPorId(Long id) {
        return autorRepository.findById(id);
    }

    // Guardar autor
    @Transactional
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    // Eliminar autor
    @Transactional
    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }

    // Actualizar autor
    @Transactional
    public Optional<Autor> actualizarAutor(Long id, Autor autorActualizado) {
        return autorRepository.findById(id)
                .map(autorExistente -> {
                    autorExistente.setNombre(autorActualizado.getNombre());
                    autorExistente.setNacionalidad(autorActualizado.getNacionalidad());
                    return autorRepository.save(autorExistente);
                });
    }
}