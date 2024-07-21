package com.williamsilva.avaliacaofilmesapi.domain.repository;

import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeFiltro;
import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class FilmeSpec {

    public static Specification<Filme> comFiltro(FilmeFiltro filtro) {
        return (root, query, builder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (filtro.ano() != null) {
                predicates.add(builder.equal(root.get("ano"), filtro.ano()));
            }

            if (filtro.vencedor() != null) {
                predicates.add(builder.equal(root.get("vencedor"), filtro.vencedor()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
