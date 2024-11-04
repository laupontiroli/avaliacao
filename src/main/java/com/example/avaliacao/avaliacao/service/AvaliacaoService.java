package com.example.avaliacao.avaliacao.service;

import com.example.avaliacao.avaliacao.model.Avaliacao;
import com.example.avaliacao.avaliacao.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public Avaliacao criar(Avaliacao avaliacao) {
        // validar aq se o filme existe
        // validar aq se o usuário assistiu o filme

        avaliacao.setId(UUID.randomUUID().toString());

        return avaliacaoRepository.save(avaliacao);
    }

    public void deletar(String id) {
        avaliacaoRepository.deleteById(id);
    }


}
