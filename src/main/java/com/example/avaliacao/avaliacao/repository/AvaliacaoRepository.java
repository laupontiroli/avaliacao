package com.example.avaliacao.avaliacao.repository;

import com.example.avaliacao.avaliacao.model.Avaliacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository  extends MongoRepository<Avaliacao, String> {


}
