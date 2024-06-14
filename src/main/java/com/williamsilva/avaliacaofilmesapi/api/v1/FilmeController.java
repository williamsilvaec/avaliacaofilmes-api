package com.williamsilva.avaliacaofilmesapi.api.v1;

import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeModel;
import com.williamsilva.avaliacaofilmesapi.api.v1.openApi.FilmeControllerOpenApi;
import com.williamsilva.avaliacaofilmesapi.domain.service.FilmeService;
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
    public List<FilmeModel> listar() {
        return filmeService.listarTodos().stream()
                .map(FilmeModel::from).toList();
    }
}
