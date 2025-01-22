package com.senai.ThymeLeaf.repositories;

import com.senai.ThymeLeaf.models.ContatoModel;
import com.senai.ThymeLeaf.models.UsuarioModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoModel, Long>{
   
    public Optional<ContatoModel> findByEmail(String email);
}
