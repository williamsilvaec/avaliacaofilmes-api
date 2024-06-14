package com.williamsilva.avaliacaofilmesapi.api.v1.openApi;

import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(name = "Filmes", description = "Gerencia os filmes")
public interface FilmeControllerOpenApi {

    @GetMapping
    @Operation(summary = "Lista todos os filmes")
    @ApiResponse(responseCode = "200", description = "Filmes listados")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor")
    List<FilmeModel> listar();
}
