package com.senai.ThymeLeaf.services;

import com.senai.ThymeLeaf.dtos.AtualizarUsuarioDto;
import com.senai.ThymeLeaf.dtos.CadastroColadoradorDto;
import com.senai.ThymeLeaf.dtos.ColaboradorDto;
import com.senai.ThymeLeaf.dtos.UsuarioDto;
import com.senai.ThymeLeaf.exceptions.ColaboradorException;
import com.senai.ThymeLeaf.exceptions.UsuarioException;
import com.senai.ThymeLeaf.models.ColaboradorModel;
import com.senai.ThymeLeaf.models.UsuarioModel;
import com.senai.ThymeLeaf.repositories.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public List<ColaboradorDto> obterListaColaboradores(){

        List<ColaboradorModel> listaColaboradorModel = colaboradorRepository.findByStatus("ativo");

        List<ColaboradorDto> listaColaborador = new ArrayList();

        for (ColaboradorModel colaborador : listaColaboradorModel){

            ColaboradorDto colaboradorDto = new ColaboradorDto();
            colaboradorDto.setId(colaborador.getId());
            colaboradorDto.setNome(colaborador.getNome());
            colaboradorDto.setEmail(colaborador.getEmail());
            colaboradorDto.setFuncao(colaborador.getFuncao());
            colaboradorDto.setDataNascimento(colaborador.getDataNascimento());

            listaColaborador.add(colaboradorDto);
        }

        return listaColaborador;

    }

    public void cadastrarColadorador(CadastroColadoradorDto cadastro) {
        Optional<ColaboradorModel> optionalColaborador = colaboradorRepository.findByEmailAndStatus(cadastro.getEmail(), "ativo");

        if (optionalColaborador.isPresent()) {
            throw new  ColaboradorException.EmailJaCadastradoException("O email já está cadastrado.");
        }

        if (cadastro.getDataNascimento().isAfter(LocalDate.now())) {
            throw new ColaboradorException.DataInvalidaException("A data de nascimento não pode ser no futuro.");
        }

        if (cadastro.getDataNascimento().isAfter(LocalDate.now().minusYears(14))) {
            throw new ColaboradorException.DataInvalidaException("O colaborador deve ter pelo menos 14 anos.");
        }

        System.out.println(cadastro.getDataNascimento());

        ColaboradorModel colaborador = new ColaboradorModel();
        colaborador.setNome(cadastro.getNome());
        colaborador.setEmail(cadastro.getEmail());
        colaborador.setFuncao(cadastro.getFuncao());
        colaborador.setDataNascimento(cadastro.getDataNascimento());

        colaboradorRepository.save(colaborador);
    }

    public void atualizarColaborador(ColaboradorDto atualizar, Long id){

        Optional<ColaboradorModel> optionalColaboradorID = colaboradorRepository.findById(id);
        // Verifica se o usuário com o ID inserido ja existe
        if(!optionalColaboradorID.isPresent()){
            throw new ColaboradorException.EmailJaCadastradoException(("O ID já está cadastrado."));
        }

        // Verifica se o usuário com o email inserido ja existe
        Optional<ColaboradorModel> optionalColaboradorEmail = colaboradorRepository.findByEmailAndStatus(atualizar.getEmail(), "ativo");
        if (optionalColaboradorEmail.isPresent() ) {
            if (!optionalColaboradorEmail.get().getEmail().equals(optionalColaboradorID.get().getEmail())) {
                throw new  UsuarioException.EmailJaCadastradoException("O email já está cadastrado.");
            }
        }

        if (atualizar.getDataNascimento().isAfter(LocalDate.now())) {
            throw new ColaboradorException.DataInvalidaException("A data de nascimento não pode ser no futuro.");
        }

        if (atualizar.getDataNascimento().isAfter(LocalDate.now().minusYears(14))) {
            throw new ColaboradorException.DataInvalidaException("O colaborador deve ter pelo menos 14 anos.");
        }

        ColaboradorModel colaborador = optionalColaboradorID.get();
        colaborador.setId(atualizar.getId());
        colaborador.setNome(atualizar.getNome());
        colaborador.setEmail(atualizar.getEmail());
        colaborador.setFuncao(atualizar.getFuncao());
        colaborador.setDataNascimento(atualizar.getDataNascimento());

        colaboradorRepository.save(colaborador);

    }

    public ColaboradorDto obterColaborador(Long id){

        Optional<ColaboradorModel> optionalColaborador = colaboradorRepository.findById(id);

        ColaboradorDto colaboradorDto = new ColaboradorDto();

        if (!optionalColaborador.isPresent()){
            colaboradorDto.setId(0L);
            return colaboradorDto;
        }

        System.out.println(optionalColaborador.get().getDataNascimento());

        colaboradorDto.setId(optionalColaborador.get().getId());
        colaboradorDto.setNome(optionalColaborador.get().getNome());
        colaboradorDto.setEmail(optionalColaborador.get().getEmail());
        colaboradorDto.setFuncao(optionalColaborador.get().getFuncao());
        colaboradorDto.setDataNascimento(optionalColaborador.get().getDataNascimento());


        return colaboradorDto;
    }
}
