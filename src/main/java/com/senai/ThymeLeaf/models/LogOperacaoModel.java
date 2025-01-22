package com.senai.ThymeLeaf.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Table (name="LOG_OPERACAO")
@Data
public class LogOperacaoModel {

    @Id
    @Column(name = "id_log")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entidade", nullable = false, length = 90)
    private String entidade;

    @Column(name = "operacao", nullable = false, length = 90)
    private String operacao;

    @Column(name = "dataHora", nullable = false, length = 90)
    private LocalDate dataHora;

    @Column(name = "usuarioResponsavel", length = 90)
    private String usuarioResponsavel;

}
