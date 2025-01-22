package com.senai.ThymeLeaf.services;

import com.senai.ThymeLeaf.dtos.EstadoDto;
import com.senai.ThymeLeaf.dtos.LoginSaveDto;
import com.senai.ThymeLeaf.models.EstadoModel;
import com.senai.ThymeLeaf.models.LogOperacaoModel;
import com.senai.ThymeLeaf.repositories.EstadoRepository;
import com.senai.ThymeLeaf.repositories.LogOperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstadoService {
    
    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    LogOperacaoRepository logrepository;
    
    public boolean cadastrarEstado(EstadoDto dados){
        
        EstadoModel estado = new EstadoModel();
        
        estado.setNome(dados.getNome());
        estado.setSigla(dados.getSigla());
        
        estadoRepository.save(estado);

        LogOperacaoModel log = new LogOperacaoModel();
        log.setEntidade("Estado");
        log.setOperacao("Cadastrar");
        log.setDataHora(LocalDate.now());
        log.setUsuarioResponsavel(LoginSaveDto.getEmail());

        logrepository.save(log);
        
        return true;
        
    }  
    
}
