package com.senai.ThymeLeaf.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CadastroColadoradorDto {

    private String nome;

    private String email;

    private String funcao;

    private LocalDate dataNascimento;
}
