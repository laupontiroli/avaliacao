package com.example.avaliacao.avaliacao.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
public class Avaliacao {

    @Id
    String id;
    String comentario;
    Integer nota;
    String idFilme;
    String email;
    LocalDateTime data;

}
