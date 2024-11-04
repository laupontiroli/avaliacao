package com.example.avaliacao.visulizacao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class HistoricoService {
    public Boolean verificaClienteAssistiu(String email) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity("http://localhost:8081/cliente/" + email, Object.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
