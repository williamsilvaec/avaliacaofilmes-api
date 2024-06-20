package com.williamsilva.avaliacaofilmesapi.api.v1;

import com.williamsilva.avaliacaofilmesapi.domain.dto.IntervalosPremiosDTO;
import com.williamsilva.avaliacaofilmesapi.domain.dto.ProdutorPremioDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProdutorControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Teste para verificar os intervalos de prêmios dos produtores vencedores")
    public void deveRetornarSucesso_quandoObterIntervalosPremios() {
        var produtorMinEsperado = new ProdutorPremioDTO("Joel Silver", 1, 1990, 1991);
        var produtorMaxEsperado = new ProdutorPremioDTO("Matthew Vaughn", 13, 2002, 2015);

        ResponseEntity<IntervalosPremiosDTO> response = restTemplate
                .getForEntity("/v1/produtores/intervalo-premios", IntervalosPremiosDTO.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        IntervalosPremiosDTO intervalos = response.getBody();

        assertThat(intervalos).isNotNull();
        assertThat(intervalos.getMin()).isNotNull();
        assertThat(intervalos.getMax()).isNotNull();

        assertThat(intervalos.getMin().size()).isEqualTo(1);
        assertThat(intervalos.getMax().size()).isEqualTo(1);

        assertEquals(intervalos.getMin().getFirst().producer(), produtorMinEsperado.producer());
        assertEquals(intervalos.getMin().getFirst().interval(), produtorMinEsperado.interval());
        assertEquals(intervalos.getMin().getFirst().previousWin(), produtorMinEsperado.previousWin());
        assertEquals(intervalos.getMin().getFirst().followingWin(), produtorMinEsperado.followingWin());

        assertEquals(intervalos.getMax().getFirst().producer(), produtorMaxEsperado.producer());
        assertEquals(intervalos.getMax().getFirst().interval(), produtorMaxEsperado.interval());
        assertEquals(intervalos.getMax().getFirst().previousWin(), produtorMaxEsperado.previousWin());
        assertEquals(intervalos.getMax().getFirst().followingWin(), produtorMaxEsperado.followingWin());
    }
}
