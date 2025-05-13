package com.alejandro_samuel.biblioteca.controller;

import com.alejandro_samuel.biblioteca.model.*;
import com.alejandro_samuel.biblioteca.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLosLibros() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    // Obtener libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    // Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizarLibro(id, libro)
                .map(libroActualizado -> new ResponseEntity<>(libroActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        if (libroService.obtenerLibroPorId(id).isPresent()) {
            libroService.eliminarLibro(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Buscar libros con filtros y ordenación
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer anio,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order) {
        
        List<Libro> libros = libroService.buscarLibros(titulo, anio, sortBy, order);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }
}