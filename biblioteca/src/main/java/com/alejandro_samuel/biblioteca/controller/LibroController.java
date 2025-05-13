package com.alejandro_samuel.biblioteca.controller;

import com.alejandro_samuel.biblioteca.model.Libro;
import com.alejandro_samuel.biblioteca.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")  // Añadir versión
public class LibroController {
    
    private final LibroService libroService;
    
    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }
    
    // Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodosLibros() {
        return ResponseEntity.ok(libroService.obtenerTodosLibros());
    }
    
    // Obtener libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Crear nuevo libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(libroService.guardarLibro(libro));
    }
    
    // Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(
            @PathVariable Long id, 
            @RequestBody Libro libro) {
        return libroService.actualizarLibro(id, libro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
    
    // Búsqueda con filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Integer anio,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        
        return ResponseEntity.ok(
            libroService.buscarLibros(titulo, anio, sortBy, order)
        );
    }
}