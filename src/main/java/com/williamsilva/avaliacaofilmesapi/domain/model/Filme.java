package com.williamsilva.avaliacaofilmesapi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ano;
    private String titulo;
    private String estudios;
    private String produtores;
    private boolean vencedor;

    public Long getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int year) {
        this.ano = year;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String title) {
        this.titulo = title;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String studios) {
        this.estudios = studios;
    }

    public String getProdutores() {
        return produtores;
    }

    public void setProdutores(String producers) {
        this.produtores = producers;
    }

    public boolean isVencedor() {
        return vencedor;
    }

    public void setVencedor(boolean winner) {
        this.vencedor = winner;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filme filme)) return false;

        return Objects.equals(id, filme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
