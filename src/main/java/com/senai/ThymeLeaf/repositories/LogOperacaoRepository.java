package com.senai.ThymeLeaf.repositories;

import com.senai.ThymeLeaf.models.ContatoModel;
import com.senai.ThymeLeaf.models.LogOperacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogOperacaoRepository extends JpaRepository<LogOperacaoModel, Long>{


    public Optional<LogOperacaoModel> findById(Long id);

}
