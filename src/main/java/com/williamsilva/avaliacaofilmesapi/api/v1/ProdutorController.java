package com.williamsilva.avaliacaofilmesapi.api.v1;

import com.williamsilva.avaliacaofilmesapi.api.v1.openApi.ProdutorControllerOpenApi;
import com.williamsilva.avaliacaofilmesapi.domain.dto.IntervalosPremiosDTO;
import com.williamsilva.avaliacaofilmesapi.domain.service.ProdutorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/produtores")
public class ProdutorController implements ProdutorControllerOpenApi {

    private final ProdutorService produtorService;

    public ProdutorController(ProdutorService produtorService) {
        this.produtorService = produtorService;
    }

    @Override
    public IntervalosPremiosDTO obterIntervalosPremios() {
        return produtorService.calcularIntervalosPremiacoes();
    }
}
