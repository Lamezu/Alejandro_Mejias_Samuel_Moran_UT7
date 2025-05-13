package com.alejandro_samuel.biblioteca.controller;

import com.alejandro_samuel.biblioteca.model.Autor;
import com.alejandro_samuel.biblioteca.model.Libro;
import com.alejandro_samuel.biblioteca.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    // Obtener todos los autores
    @GetMapping
    public ResponseEntity<List<Autor>> obtenerTodosLosAutores() {
        List<Autor> autores = autorService.obtenerTodosLosAutores();
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    // Obtener autor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id) {
        return autorService.obtenerAutorPorId(id)
                .map(autor -> new ResponseEntity<>(autor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo autor
    @PostMapping
    public ResponseEntity<Autor> crearAutor(@RequestBody Autor autor) {
        Autor nuevoAutor = autorService.guardarAutor(autor);
        return new ResponseEntity<>(nuevoAutor, HttpStatus.CREATED);
    }

    // Actualizar autor
    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        return autorService.actualizarAutor(id, autor)
                .map(autorActualizado -> new ResponseEntity<>(autorActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar autor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable Long id) {
        if (autorService.obtenerAutorPorId(id).isPresent()) {
            autorService.eliminarAutor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // En AutorController.java
    @GetMapping("/{id}/libros")
    public ResponseEntity<List<Libro>> obtenerLibrosDeAutor(@PathVariable Long id) {
    return ResponseEntity.ok(autorService.obtenerLibrosDeAutor(id));
    }
}