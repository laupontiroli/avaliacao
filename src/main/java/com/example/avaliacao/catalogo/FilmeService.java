package com.example.avaliacao.catalogo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class FilmeService {
    public Boolean verificaFilmeExiste(String email) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Filme> response = restTemplate.getForEntity("http://localhost:8082/filme/" + email, Filme.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
