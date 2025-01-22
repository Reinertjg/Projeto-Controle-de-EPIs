package com.senai.ThymeLeaf.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table (name="CONTATO")
@Data
public class ContatoModel {
    
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", nullable = false, length = 90)
    private String email;
    
    @Column(name = "nome", nullable = false, length = 90)
    private String nome;
    
    @Column(name = "telefone", nullable = false, length = 90)
    private String telefone;
    
    @Column(name = "cpf/cnpj", nullable = false, length = 90)
    private String cpf;
    
    @Column(name = "dataDeCadastro", nullable = false, length = 90)
    private LocalDate dataDeCadastro;
    
     @Column(name = "endereco", nullable = false, length = 90)
    private String endereco;
    
}
