package com.williamsilva.avaliacaofilmesapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Healthckeck", description = "Verifica se a aplicação está ativa")
@RestController
public class HealthCheckController {

    @Operation(summary = "Retorna o status da aplicação")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor")
    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "OK";
    }
}
