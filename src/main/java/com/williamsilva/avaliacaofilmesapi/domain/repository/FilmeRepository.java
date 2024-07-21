package com.williamsilva.avaliacaofilmesapi.domain.repository;

import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmeRepository extends JpaRepository<Filme, Long>, JpaSpecificationExecutor<Filme> {
}
