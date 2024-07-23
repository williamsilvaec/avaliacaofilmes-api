package com.williamsilva.avaliacaofilmesapi.domain.repository;

import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import com.williamsilva.avaliacaofilmesapi.domain.repository.projections.AnoContagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long>, JpaSpecificationExecutor<Filme> {

    @Query("SELECT f.ano as ano, COUNT(f) as contagem FROM Filme f WHERE f.vencedor = true GROUP BY f.ano HAVING COUNT(f) > 1")
    List<AnoContagem> encontrarAnosComMaisDeUmVencedor();

}
