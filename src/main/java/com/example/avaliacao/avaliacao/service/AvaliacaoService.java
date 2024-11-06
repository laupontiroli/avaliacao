package com.example.avaliacao.avaliacao.service;

import com.example.avaliacao.avaliacao.model.Avaliacao;
import com.example.avaliacao.avaliacao.model.EditarAvaliacaoDTO;
import com.example.avaliacao.avaliacao.repository.AvaliacaoRepository;
import com.example.avaliacao.catalogo.FilmeService;
import com.example.avaliacao.visulizacao.VisualizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    FilmeService filmeService;

    @Autowired
    VisualizacaoService visualizacaoService;

    public Avaliacao criar(Avaliacao avaliacao, String jwtToken) {


        if (!filmeService.verificaFilmeExiste(avaliacao.getIdFilme(),jwtToken)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado");
        }

        if (!visualizacaoService.verificaClienteAssistiu(jwtToken)) {
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

    public Double mediaAvaliacoes(String idFilme) {
        return avaliacaoRepository.findAll().stream()
                .filter(avaliacao -> avaliacao.getIdFilme().equals(idFilme))
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0);
    }

    public Avaliacao atualizar(EditarAvaliacaoDTO avaliacaoDTO){

        Optional<Avaliacao> op = avaliacaoRepository.findById(avaliacaoDTO.getId());
        if (op.isPresent()){
            Avaliacao avaliacao = op.get();
            avaliacao.setComentario(avaliacaoDTO.getComentario());
            avaliacao.setNota(avaliacaoDTO.getNota());
            return avaliacaoRepository.save(avaliacao);
        }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada");
    }

}
