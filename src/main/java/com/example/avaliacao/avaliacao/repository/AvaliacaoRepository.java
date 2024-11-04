package com.example.avaliacao.avaliacao.repository;

import com.example.avaliacao.avaliacao.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AvaliacaoRepository  extends MongoRepository<Avaliacao, String> {

    ArrayList<Avaliacao> findByIdFilmeOrderByData(String idFilme);

    ArrayList<Avaliacao> findByIdFilmeOrderByNota(String idFilme);

    ArrayList<Avaliacao> findByIdFilme(String idFilme);
}
