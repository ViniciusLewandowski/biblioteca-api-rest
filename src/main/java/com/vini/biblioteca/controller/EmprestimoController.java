package com.vini.biblioteca.controller;

import com.vini.biblioteca.models.Emprestimo;
import com.vini.biblioteca.models.Livro;
import com.vini.biblioteca.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public Emprestimo emprestar(
            @RequestParam Long usuarioId,
            @RequestParam Long livroId,
            @RequestParam int quantidade
    ) {
        return emprestimoService.emprestarLivro(usuarioId, livroId, quantidade);
    }

    @PostMapping("/devolver/{id}")
    public void devolver(@PathVariable Long id) {
        emprestimoService.devolverLivro(id);
    }

    @GetMapping
    public List<Emprestimo> listar() {
        return emprestimoService.listarEmprestimos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Emprestimo> listarPorUsuario(@PathVariable Long usuarioId) {
        return emprestimoService.buscarPorUsuario(usuarioId);
    }
}
