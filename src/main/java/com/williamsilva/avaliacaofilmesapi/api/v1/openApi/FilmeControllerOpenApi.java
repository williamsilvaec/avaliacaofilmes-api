package com.williamsilva.avaliacaofilmesapi.api.v1.openApi;

import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeFiltro;
import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Filmes", description = "Gerencia os filmes")
public interface FilmeControllerOpenApi {

    @GetMapping
    @Operation(summary = "Lista todos os filmes ordenados pelo ano de premiação (do mais antigo para o mais recente)")
    @ApiResponse(responseCode = "200", description = "Filmes listados")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor")
    Page<FilmeModel> listar(FilmeFiltro filmeFiltro, Pageable pageable);
}
