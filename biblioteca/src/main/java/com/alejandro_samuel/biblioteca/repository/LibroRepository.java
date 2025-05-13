package com.alejandro_samuel.biblioteca.repository;

import com.alejandro_samuel.biblioteca.model.Libro;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long>{

    List<Libro> buscarLibros(String titulo, Integer anio, Sort sort);
    
}
