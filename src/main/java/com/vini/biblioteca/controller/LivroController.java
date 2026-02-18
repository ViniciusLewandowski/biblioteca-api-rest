package com.vini.biblioteca.controller;

import com.vini.biblioteca.models.Livro;
import com.vini.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public Livro criar(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @GetMapping
    public List<Livro> listar() {
        return livroRepository.findAll();
    }
}
