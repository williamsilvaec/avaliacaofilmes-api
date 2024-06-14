package com.williamsilva.avaliacaofilmesapi.api.v1.model;

import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;

public record FilmeModel(Long id, int ano, String titulo, String estudios, String produtores, boolean vencedor) {

    private FilmeModel(Filme filme) {
        this(filme.getId(), filme.getAno(), filme.getTitulo(), filme.getEstudios(), filme.getProdutores(),
                filme.isVencedor());
    }

    public static FilmeModel from(Filme filme) {
        return new FilmeModel(filme);
    }
}
