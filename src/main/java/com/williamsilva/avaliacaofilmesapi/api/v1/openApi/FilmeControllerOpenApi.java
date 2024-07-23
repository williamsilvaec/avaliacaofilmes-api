package com.williamsilva.avaliacaofilmesapi.api.v1.openApi;

import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeFiltro;
import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Filmes", description = "Gerencia os filmes")
public interface FilmeControllerOpenApi {

    @GetMapping
    @Operation(summary = "Pesquisa os filmes filtrando por ano e vencedor ")
    @ApiResponse(responseCode = "200", description = "Retornar uma página de filmes de acordo com o filtro")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor")
    Page<FilmeModel> listar(FilmeFiltro filmeFiltro, Pageable pageable);

    @GetMapping("/anos-com-mais-de-um-vencedor")
    @Operation(summary = "Lista os anos que tiveram mais de um filme vencedor")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista de anos com mais de um vencedor")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor")
    ResponseEntity<?> listarVencedores();

    @GetMapping("/top-tres-estudios-vencedores")
    @Operation(summary = "Lista o TOP 3 estúdios com mais filmes vencedores")
    @ApiResponse(responseCode = "200", description = "Retorna o TOP 3 estúdios com mais filmes vencedores")
    @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor")
    ResponseEntity<?> listarEstudiosComMaisDeUmVencedor();
}
