package com.example.avaliacao.catalogo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class Filme {
    private String id;
    private String titulo;
    private String descricao;
    private String genero;
    private Integer ano;

    private String classificacao;

    private ArrayList<String> diretores;
    private ArrayList<String> atores;
}
