package com.alejandro_samuel.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alejandro_samuel.biblioteca.model.Autor;


public interface AutorRepository extends JpaRepository<Autor, Long> {
}