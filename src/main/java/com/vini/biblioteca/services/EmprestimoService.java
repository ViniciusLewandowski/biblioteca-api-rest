package com.vini.biblioteca.services;

import com.vini.biblioteca.models.Emprestimo;
import com.vini.biblioteca.models.Livro;
import com.vini.biblioteca.models.Usuario;
import com.vini.biblioteca.repositories.EmprestimoRepository;
import com.vini.biblioteca.repositories.LivroRepository;
import com.vini.biblioteca.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vini.biblioteca.repositories.UsuarioRepository;
import com.vini.biblioteca.repositories.LivroRepository;
import com.vini.biblioteca.repositories.EmprestimoRepository;


import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Emprestimo emprestarLivro(Long usuarioId, Long livroId, int quantidade) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (quantidade <= 0 || quantidade > livro.getCopiasDisponiveis()) {
            throw new RuntimeException("Quantidade inválida");
        }

        livro.setCopiasDisponiveis(
                livro.getCopiasDisponiveis() - quantidade
        );

        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo(usuario, livro, quantidade);

        return emprestimoRepository.save(emprestimo);
    }

    public void devolverLivro(Long emprestimoId) {

        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (!emprestimo.isAtivo()) {
            throw new RuntimeException("Empréstimo já devolvido");
        }

        Livro livro = emprestimo.getLivro();

        livro.setCopiasDisponiveis(
                livro.getCopiasDisponiveis() + emprestimo.getQuantidadeEmprestada()
        );

        livroRepository.save(livro);

        emprestimo.desativar();

        emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> buscarPorUsuario(Long usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId);
    }
}
