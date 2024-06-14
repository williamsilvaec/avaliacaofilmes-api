package com.williamsilva.avaliacaofilmesapi.domain.service;

import com.williamsilva.avaliacaofilmesapi.domain.dto.IntervalosPremiosDTO;
import com.williamsilva.avaliacaofilmesapi.domain.dto.ProdutorPremioDTO;
import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import com.williamsilva.avaliacaofilmesapi.domain.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutorService {

    private final FilmeRepository filmeRepository;

    public ProdutorService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public IntervalosPremiosDTO calcularIntervalosPremiacoes() {
        List<Filme> FilmesVencedores = filmeRepository.findAll().stream()
                .filter(Filme::isVencedor)
                .toList();

        Map<String, List<Integer>> produtorVencedor = new HashMap<>();

        for (Filme filmeVencedor : FilmesVencedores) {
            String[] produtores = filmeVencedor.getProdutores().split(",\\s*");
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

                ProdutorPremioDTO produtorPremio = new ProdutorPremioDTO();
                produtorPremio.setProducer(produtor);
                produtorPremio.setInterval(intervalo);
                produtorPremio.setPreviousWin(anos.get(i - 1));
                produtorPremio.setFollowingWin(anos.get(i));

                if (intervalosMin.isEmpty() || intervalo < intervalosMin.getFirst().getInterval()) {
                    intervalosMin.clear();
                    intervalosMin.add(produtorPremio);
                } else if (intervalo == intervalosMin.getFirst().getInterval()) {
                    intervalosMin.add(produtorPremio);
                }

                if (intervalosMax.isEmpty() || intervalo > intervalosMax.getFirst().getInterval()) {
                    intervalosMax.clear();
                    intervalosMax.add(produtorPremio);
                } else if (intervalo == intervalosMax.getFirst().getInterval()) {
                    intervalosMax.add(produtorPremio);
                }
            }
        }

        IntervalosPremiosDTO resultado = new IntervalosPremiosDTO();
        resultado.setMin(intervalosMin);
        resultado.setMax(intervalosMax);

        return resultado;
    }


}
