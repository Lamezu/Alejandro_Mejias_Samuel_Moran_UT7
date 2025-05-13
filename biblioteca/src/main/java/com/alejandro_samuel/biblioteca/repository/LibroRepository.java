package com.alejandro_samuel.biblioteca.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alejandro_samuel.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    @Query("SELECT l FROM Libro l WHERE " +
           "(:titulo IS NULL OR l.titulo LIKE %:titulo%) AND " +
           "(:anioPublicacion IS NULL OR l.anioPublicacion = :anioPublicacion)")
    List<Libro> buscarLibros(
            @Param("titulo") String titulo,
            @Param("anioPublicacion") Integer anioPublicacion,
            Sort sort);
}

