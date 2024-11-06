package com.example.avaliacao.visulizacao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Visualizacao {
    public Integer filmeId;
    public String tituloFilme;
    public LocalDate dataVisualizacao;
    public int tempoAssistido;

}
