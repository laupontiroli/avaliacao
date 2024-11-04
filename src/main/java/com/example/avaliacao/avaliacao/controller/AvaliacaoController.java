package com.example.avaliacao.avaliacao.controller;

import com.example.avaliacao.avaliacao.model.Avaliacao;
import com.example.avaliacao.avaliacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
}
