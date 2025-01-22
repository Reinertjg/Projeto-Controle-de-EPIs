package com.senai.ThymeLeaf.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="COLABORADOR")
@Data
public class ColaboradorModel {

    @Id
    @Column(name = "id_colaborador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 90)
    private String nome;

    @Column(name = "email", nullable = false, length = 90)
    private String email;

    @Column(name = "funcao", nullable = false, length = 90)
    private String funcao;

    @Column(name = "dataNascimento", nullable = false, length = 90)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column(name = "status", nullable = false, length = 90)
    private String status = "ativo";
}
