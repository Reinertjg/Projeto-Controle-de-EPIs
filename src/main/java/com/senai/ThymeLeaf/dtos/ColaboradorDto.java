package com.senai.ThymeLeaf.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ColaboradorDto {

    private Long id;

    private String nome;

    private String email;

    private String funcao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
}
