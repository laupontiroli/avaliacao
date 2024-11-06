package com.example.avaliacao.visulizacao;

import com.example.avaliacao.catalogo.Filme;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VisualizacaoService {
    public Boolean verificaClienteAssistiu( String jwtToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<List<Visualizacao>> response = restTemplate.exchange(
                    "http://3.92.236.111:8083/visualizacoes/usuarios",
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Visualizacao>>() {}
            );
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
