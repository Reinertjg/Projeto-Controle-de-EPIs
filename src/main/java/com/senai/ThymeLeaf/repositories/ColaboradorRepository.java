package com.senai.ThymeLeaf.repositories;

import com.senai.ThymeLeaf.models.ColaboradorModel;
import com.senai.ThymeLeaf.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel,Long> {
    
    //- Método que realiza o select no banco de dados filtrando no where o email do usuário
    public Optional<ColaboradorModel> findByEmailAndStatus(String email, String status);

    List<ColaboradorModel> findByStatus(String status);
}