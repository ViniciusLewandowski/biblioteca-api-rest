package com.vini.biblioteca.models;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private int anoDeLancamento;
    private int copiasDisponiveis;

    public Livro() {
    }

    public Livro(String titulo, String autor, int anoDeLancamento, int copiasDisponiveis) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoDeLancamento = anoDeLancamento;
        this.copiasDisponiveis = copiasDisponiveis;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public int getCopiasDisponiveis() {
        return copiasDisponiveis;
    }

    public void setCopiasDisponiveis(int copiasDisponiveis) {
        this.copiasDisponiveis = copiasDisponiveis;
    }
}
