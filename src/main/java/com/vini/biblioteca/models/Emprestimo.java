package com.vini.biblioteca.models;

import jakarta.persistence.*;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private int quantidadeEmprestada;

    private boolean ativo = true;

    // Construtor vazio obrigatório
    public Emprestimo() {
    }

    public Emprestimo(Usuario usuario, Livro livro, int quantidadeEmprestada) {
        this.usuario = usuario;
        this.livro = livro;
        this.quantidadeEmprestada = quantidadeEmprestada;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void desativar() {
        this.ativo = false;
    }
}
