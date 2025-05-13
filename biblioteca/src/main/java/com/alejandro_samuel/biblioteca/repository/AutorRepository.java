package com.alejandro_samuel.biblioteca.repository;

import com.alejandro_samuel.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Métodos personalizados de consulta si son necesarios
}