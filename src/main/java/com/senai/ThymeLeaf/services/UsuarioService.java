/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.ThymeLeaf.services;

import com.senai.ThymeLeaf.dtos.*;
import com.senai.ThymeLeaf.exceptions.UsuarioException;
import com.senai.ThymeLeaf.models.LogOperacaoModel;
import com.senai.ThymeLeaf.models.UsuarioModel;
import com.senai.ThymeLeaf.repositories.LogOperacaoRepository;
import com.senai.ThymeLeaf.repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import static com.senai.ThymeLeaf.dtos.LoginSaveDto.getEmail;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LogOperacaoRepository logrepository;
        
    public boolean validarLogin(LoginDto loginDto){
              
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findByEmailAndStatus(loginDto.getEmail(), "ativo");
        
        //--Verifica se achou o usuário no banco de dados pelo Email
        if (!optionalUsuario.isPresent()){
            //--Se não achou retorna erro
            return false;
        }
        
        //--Se achou o usuário pelo e-mail verifica se a senha esta correta!
        if (!optionalUsuario.get().getSenha().equals(loginDto.getSenha())){
            //--Se não esta correta retorna erro
            return false;
        }

        LoginSaveDto.setEmail(optionalUsuario.get().getEmail());

        return true;
    }
    
    public List<UsuarioDto> obterListaUsuarios(){
        
        List<UsuarioModel> listaUsuarioModel = usuarioRepository.findByStatus("ativo");
        
        List<UsuarioDto> listaUsuario = new ArrayList();
        
        for (UsuarioModel usuario : listaUsuarioModel){
            
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setId(usuario.getId());
            usuarioDto.setNome(usuario.getNome());
            usuarioDto.setEmail(usuario.getEmail());
            
            listaUsuario.add(usuarioDto);
        }
        
        return listaUsuario;
        
    }

    public void cadastrarUsuario(CadastroDto cadastro) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findByEmailAndStatus(cadastro.getEmail(), "ativo");

        if (optionalUsuario.isPresent()) {
            throw new  UsuarioException.EmailJaCadastradoException("O email já está cadastrado.");
        }

        if (cadastro.getSenha().length() <= 5) {
            throw new UsuarioException.SenhaInvalidaException("A senha deve ter mais de 5 caracteres.");
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(cadastro.getNome());
        usuario.setEmail(cadastro.getEmail());
        usuario.setSenha(cadastro.getSenha());

        usuarioRepository.save(usuario);

        LogOperacaoModel log = new LogOperacaoModel();
        log.setEntidade("Usuario");
        log.setOperacao("Cadastrar");
        log.setDataHora(LocalDate.now());
        log.setUsuarioResponsavel(getEmail());

        logrepository.save(log);
    }

    public void atualizarUsuario(AtualizarUsuarioDto atualizar, Long id){

        Optional<UsuarioModel> optionalUsuarioID = usuarioRepository.findById(id);
        // Verifica se o usuário com o ID inserido ja existe
        if(!optionalUsuarioID.isPresent()){
            throw new  UsuarioException.EmailJaCadastradoException("O ID já está cadastrado.");
        }

        // Verifica se o usuário com o email inserido ja existe
        Optional<UsuarioModel> optionalUsuarioEmail = usuarioRepository.findByEmailAndStatus(atualizar.getEmail(), "ativo");
        if (optionalUsuarioEmail.isPresent() ) {
            if (!optionalUsuarioEmail.get().getEmail().equals(optionalUsuarioID.get().getEmail())) {
                throw new  UsuarioException.EmailJaCadastradoException("O email já está cadastrado.");
            }
        }

        if (atualizar.getSenha().length() <= 5) {
            throw new UsuarioException.SenhaInvalidaException("A senha deve ter mais de 5 caracteres.");
        }

        UsuarioModel usuario = optionalUsuarioID.get();
        usuario.setId(atualizar.getId());
        usuario.setNome(atualizar.getNome());
        usuario.setEmail(atualizar.getEmail());
        usuario.setSenha(atualizar.getSenha());

        usuarioRepository.save(usuario);

    }

    public boolean excluirUsuario(Long id){
        
        System.out.println("id:" + id);
        
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
        
        if (!optionalUsuario.isPresent()){
            return false;
        }
        
        usuarioRepository.delete(optionalUsuario.get());

        LogOperacaoModel log = new LogOperacaoModel();
        log.setEntidade("Usuario");
        log.setOperacao("Excluir");
        log.setDataHora(LocalDate.now());
        log.setUsuarioResponsavel(getEmail());

        logrepository.save(log);

        return true;
        
    }
    
    public UsuarioDto obterUsuario(Long id){
        
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);

        UsuarioDto usuarioDto = new UsuarioDto();
        
        if (!optionalUsuario.isPresent()){            
            usuarioDto.setId(0L);
            return usuarioDto;
        }

        usuarioDto.setId(optionalUsuario.get().getId());
        usuarioDto.setNome(optionalUsuario.get().getNome());
        usuarioDto.setEmail(optionalUsuario.get().getEmail());

        LogOperacaoModel log = new LogOperacaoModel();
        log.setEntidade("Usuario");
        log.setOperacao("Vizualizar");
        log.setDataHora(LocalDate.now());
        log.setUsuarioResponsavel(getEmail());

        logrepository.save(log);
        
        return usuarioDto;
    }


}
