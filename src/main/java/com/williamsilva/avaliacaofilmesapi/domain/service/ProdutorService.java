package com.williamsilva.avaliacaofilmesapi.domain.service;

import com.williamsilva.avaliacaofilmesapi.domain.dto.IntervalosPremiosDTO;
import com.williamsilva.avaliacaofilmesapi.domain.dto.ProdutorPremioDTO;
import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutorService {

    private final FilmeService filmeService;

    public ProdutorService(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    public IntervalosPremiosDTO calcularIntervalosPremiacoes() {
        List<Filme> FilmesVencedores = filmeService.listarVencedores();

        Map<String, List<Integer>> produtorVencedor = new HashMap<>();

        for (Filme filmeVencedor : FilmesVencedores) {
            String[] produtores = filmeVencedor.getProdutores().split(",\\s*|\\s+and\\s+");
            for (String produtor : produtores) {
                produtorVencedor.putIfAbsent(produtor, new ArrayList<>());
                produtorVencedor.get(produtor).add(filmeVencedor.getAno());
            }
        }

        List<ProdutorPremioDTO> intervalosMin = new ArrayList<>();
        List<ProdutorPremioDTO> intervalosMax = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : produtorVencedor.entrySet()) {
            String produtor = entry.getKey();
            List<Integer> anos = entry.getValue();
            Collections.sort(anos);

            if (anos.size() < 2) {
                continue;
            }

            for (int i = 1; i < anos.size(); i++) {
                int intervalo = anos.get(i) - anos.get(i - 1);

                var produtorPremio = new ProdutorPremioDTO(produtor, intervalo, anos.get(i - 1), anos.get(i));

                if (intervalosMin.isEmpty() || intervalo < intervalosMin.getFirst().intervalo()) {
                    intervalosMin.clear();
                    intervalosMin.add(produtorPremio);
                } else if (intervalo == intervalosMin.getFirst().intervalo()) {
                    intervalosMin.add(produtorPremio);
                }

                if (intervalosMax.isEmpty() || intervalo > intervalosMax.getFirst().intervalo()) {
                    intervalosMax.clear();
                    intervalosMax.add(produtorPremio);
                } else if (intervalo == intervalosMax.getFirst().intervalo()) {
                    intervalosMax.add(produtorPremio);
                }
            }
        }

        var resultado = new IntervalosPremiosDTO();
        resultado.setMin(intervalosMin);
        resultado.setMax(intervalosMax);

        return resultado;
    }

}

