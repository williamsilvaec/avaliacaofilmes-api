package com.williamsilva.avaliacaofilmesapi.domain.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.williamsilva.avaliacaofilmesapi.api.v1.model.FilmeFiltro;
import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import com.williamsilva.avaliacaofilmesapi.domain.repository.FilmeRepository;
import com.williamsilva.avaliacaofilmesapi.domain.repository.FilmeSpec;
import com.williamsilva.avaliacaofilmesapi.domain.repository.projections.AnoContagem;
import com.williamsilva.avaliacaofilmesapi.domain.repository.projections.EstudioContagem;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @PostConstruct
    public void init() throws IOException, CsvValidationException {
        ClassPathResource csvFile = new ClassPathResource("movies.csv");

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(csvFile.getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            reader.readNext();

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 5) {
                    continue;
                }

                Filme filme = new Filme();
                filme.setAno(Integer.parseInt(nextLine[0]));
                filme.setTitulo(nextLine[1]);
                filme.setEstudios(nextLine[2]);
                filme.setProdutores(nextLine[3]);
                filme.setVencedor("yes".equalsIgnoreCase(nextLine[4]));

                filmeRepository.save(filme);
            }
        }
    }

    @Transactional(readOnly = true)
    public Page<Filme> listarTodos(FilmeFiltro filtro, Pageable pageable) {
        return filmeRepository.findAll(FilmeSpec.comFiltro(filtro), pageable);
    }

    public List<Filme> listarVencedores() {
        return filmeRepository.findAll().stream().filter(Filme::isVencedor).toList();
    }

    public List<AnoContagem> obterAnosComMaisDeUmVencedor() {
        return filmeRepository.encontrarAnosComMaisDeUmVencedor();
    }

    public List<EstudioContagem> obterEstudiosComMaisDeUmVencedor() {
        List<Filme> filmesVencedores = listarVencedores();

        Map<String, Integer> contagemPorEstudio = new HashMap<>();
        filmesVencedores.forEach(filme -> {
            Arrays.stream(filme.getEstudios().split(",\\s*"))
                    .forEach(estudio -> contagemPorEstudio.merge(estudio, 1, Integer::sum));
        });

        return contagemPorEstudio.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .map(entry -> new EstudioContagem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
