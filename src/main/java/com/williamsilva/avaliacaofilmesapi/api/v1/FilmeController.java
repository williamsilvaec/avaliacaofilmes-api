package com.williamsilva.avaliacaofilmesapi.api.v1;

import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeFiltro;
import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeModel;
import com.williamsilva.avaliacaofilmesapi.api.v1.openApi.FilmeControllerOpenApi;
import com.williamsilva.avaliacaofilmesapi.domain.repository.projections.AnoContagem;
import com.williamsilva.avaliacaofilmesapi.domain.repository.projections.EstudioContagem;
import com.williamsilva.avaliacaofilmesapi.domain.service.FilmeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/filmes")
public class FilmeController implements FilmeControllerOpenApi {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @Override
    public Page<FilmeModel> listar(FilmeFiltro filtro, Pageable pageable) {
        return filmeService.listarTodos(filtro, pageable).map(FilmeModel::from);
    }

    @Override
    public ResponseEntity<?> listarVencedores() {
        List<AnoContagem> anoContagems = filmeService.obterAnosComMaisDeUmVencedor();
        return ResponseEntity.ok(anoContagems);
    }

    @Override
    public ResponseEntity<?> listarEstudiosComMaisDeUmVencedor() {
        List<EstudioContagem> estudioContagems = filmeService.obterEstudiosComMaisDeUmVencedor();
        return ResponseEntity.ok(estudioContagems);
    }
}
