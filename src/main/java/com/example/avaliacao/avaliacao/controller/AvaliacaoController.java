package com.example.avaliacao.avaliacao.controller;

import com.example.avaliacao.avaliacao.model.Avaliacao;
import com.example.avaliacao.avaliacao.model.EditarAvaliacaoDTO;
import com.example.avaliacao.avaliacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao avaliacaoSalva = avaliacaoService.criar(avaliacao);
        return ResponseEntity.ok(avaliacaoSalva);
    }

    @GetMapping("/media")
    public ResponseEntity<Double> mediaAvaliacoes(@RequestParam String idFilme) {
        Double media = avaliacaoService.mediaAvaliacoes(idFilme);
        return ResponseEntity.ok(media);
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes(@RequestParam String idFilme, @RequestParam(required = false) String ordenacao) {
        List<Avaliacao> avaliacoes = avaliacaoService.listar(idFilme, ordenacao);
        return ResponseEntity.ok(avaliacoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable String id) {
        avaliacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Avaliacao> editarAvaliacao(@RequestBody EditarAvaliacaoDTO avaliacao) {
        Avaliacao avaliacaoEditada = avaliacaoService.atualizar(avaliacao);
        return ResponseEntity.ok(avaliacaoEditada);
    }

}
