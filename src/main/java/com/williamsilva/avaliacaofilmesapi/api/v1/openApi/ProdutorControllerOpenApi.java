package com.williamsilva.avaliacaofilmesapi.api.v1.openApi;

import com.williamsilva.avaliacaofilmesapi.domain.dto.IntervalosPremiosDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Produtores", description = "Gerencia os produtores de filmes")
public interface ProdutorControllerOpenApi {

    @GetMapping("/intervalo-premios")
    @Operation(summary = "Obtém o produtor com maior intervalo entre dois prêmios consecutivos, e o que\n" +
            "obteve dois prêmios mais rápido")
    @ApiResponse(responseCode = "200", description = "Intervalos de prêmios")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor")
    IntervalosPremiosDTO obterIntervalosPremios();

}
