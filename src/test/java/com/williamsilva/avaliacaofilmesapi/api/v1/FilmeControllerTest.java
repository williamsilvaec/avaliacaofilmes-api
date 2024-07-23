package com.williamsilva.avaliacaofilmesapi.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import com.williamsilva.avaliacaofilmesapi.domain.repository.projections.AnoContagem;
import com.williamsilva.avaliacaofilmesapi.domain.repository.projections.EstudioContagem;
import com.williamsilva.avaliacaofilmesapi.domain.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmeController.class)
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listar_DeveRetornarListaDeFilmes_QuandoChamadoComFiltroEPageable() throws Exception {
        Filme filmeEsperado = new Filme();
        filmeEsperado.setAno(1990);
        filmeEsperado.setTitulo("Teste");
        filmeEsperado.setEstudios("Estudio");
        filmeEsperado.setProdutores("Produtor");

        PageImpl<Filme> paginaEsperada = new PageImpl<>(Collections.singletonList(filmeEsperado));
        String paginaEsperadaJson = objectMapper.writeValueAsString(paginaEsperada);

        when(filmeService.listarTodos(any(), any())).thenReturn(paginaEsperada);

        mockMvc.perform(get("/v1/filmes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(paginaEsperadaJson, true));
    }

    @Test
    void listarVencedores() throws Exception {
        var anoContagemEsperado = new AnoContagem() {
            @Override
            public Integer getAno() {
                return 2020;
            }

            @Override
            public Integer getContagem() {
                return 10;
            }
        };

        List<AnoContagem> resultadoEsperado = Collections.singletonList(anoContagemEsperado);
        String jsonEsperado = objectMapper.writeValueAsString(resultadoEsperado);

        when(filmeService.obterAnosComMaisDeUmVencedor()).thenReturn(resultadoEsperado);

        mockMvc.perform(get("/v1/filmes/anos-com-mais-de-um-vencedor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonEsperado, true));
    }

    @Test
    void listarEstudiosComMaisDeUmVencedor() throws Exception {
        var estudioContagemEsperada = new EstudioContagem("Warner Bros", 500);
        List<EstudioContagem> resultadoEsperado = Collections.singletonList(estudioContagemEsperada);
        String jsonEsperado = objectMapper.writeValueAsString(resultadoEsperado);

        when(filmeService.obterEstudiosComMaisDeUmVencedor()).thenReturn(resultadoEsperado);

        mockMvc.perform(get("/v1/filmes/top-tres-estudios-vencedores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonEsperado, true));
    }
}
