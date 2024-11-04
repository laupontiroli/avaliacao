package com.example.avaliacao.avaliacao.service;

import com.example.avaliacao.avaliacao.model.Avaliacao;
import com.example.avaliacao.avaliacao.model.EditarAvaliacaoDTO;
import com.example.avaliacao.avaliacao.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public Boolean verificaFilmeExiste(String id) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity("http://localhost:8082/filme/" + id, Object.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean verificaClienteAssistiu(String email) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity("http://localhost:8081/cliente/" + email, Object.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public Avaliacao criar(Avaliacao avaliacao) {
        if (!verificaFilmeExiste(avaliacao.getIdFilme())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }

        if (!verificaClienteAssistiu(avaliacao.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não assistiu o filme");
        }

        avaliacao.setId(UUID.randomUUID().toString());

        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listar(String idFilme, String ordenacao) {
        if (idFilme != null && ordenacao == null) {
            return avaliacaoRepository.findByIdFilme(idFilme);
        }
        if (idFilme != null && ordenacao.equals("nota")) {
            return avaliacaoRepository.findByIdFilmeOrderByNota(idFilme);
        }
        if (idFilme != null && ordenacao.equals("data")) {
            return avaliacaoRepository.findByIdFilmeOrderByData(idFilme);
        }

        return avaliacaoRepository.findAll();
    }

    public void deletar(String id) {
        avaliacaoRepository.deleteById(id);
    }

    public Avaliacao atualizar(EditarAvaliacaoDTO avaliacaoDTO){

        Optional<Avaliacao> op = avaliacaoRepository.findById(avaliacaoDTO.getId());
        if (op.isPresent()){
            Avaliacao avaliacao = op.get();
            avaliacao.setComentario(avaliacaoDTO.getComentario());
            avaliacao.setNota(avaliacaoDTO.getNota());
            return avaliacaoRepository.save(avaliacao);
        }
        throw new RuntimeException("Avalicao não encontrada");
    }

}
