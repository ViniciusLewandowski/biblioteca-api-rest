package com.vini.biblioteca.repositories;

import com.vini.biblioteca.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
