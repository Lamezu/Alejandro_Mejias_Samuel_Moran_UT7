package com.alejandro_samuel.biblioteca.repository;

import com.alejandro_samuel.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AutorRepository extends JpaRepository<Autor, Long> {
}