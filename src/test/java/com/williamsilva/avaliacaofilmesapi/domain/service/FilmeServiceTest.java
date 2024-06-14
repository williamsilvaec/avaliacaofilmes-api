package com.williamsilva.avaliacaofilmesapi.domain.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.williamsilva.avaliacaofilmesapi.domain.model.Filme;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmeServiceTest {

    @Autowired
    private FilmeService filmeService;

    @Test
    @DisplayName("Deve verificar se os dados do CSV estão corretos")
    public void testLoadDataFromCsv() throws IOException, CsvValidationException {
        List<Filme> filmes = filmeService.listarTodos();

        ClassPathResource csvFile = new ClassPathResource("test_movies.csv");
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(csvFile.getInputStream()))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            reader.readNext();

            String[] nextLine;
            int index = 0;
            while ((nextLine = reader.readNext()) != null) {
                Filme filme = filmes.get(index++);
                assertEquals(Integer.parseInt(nextLine[0]), filme.getAno());
                assertEquals(nextLine[1], filme.getTitulo());
                assertEquals(nextLine[2], filme.getEstudios());
                assertEquals(nextLine[3], filme.getProdutores());
                assertEquals("yes".equalsIgnoreCase(nextLine[4]), filme.isVencedor());
            }
        }
    }
}