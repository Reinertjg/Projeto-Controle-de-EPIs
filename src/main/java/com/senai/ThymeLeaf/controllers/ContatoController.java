package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.CadastroContatoDto;
import com.senai.ThymeLeaf.dtos.ContatoDto;
import com.senai.ThymeLeaf.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contato")
public class ContatoController {
    
    @Autowired
    ContatoService contatoService;
    
    @PostMapping()
    public String cadastrarContato(@ModelAttribute("contatoDto") CadastroContatoDto cadastro){

        boolean sucesso = contatoService.cadastrarContato(cadastro);
        
        if (sucesso){
            return "redirect:listacontato";
        }

        return "redirect:cadastrarcontato?erro";        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String>  excluirContato(@PathVariable Long id){
    
        boolean sucesso = contatoService.excluirContato(id);
        
        if (sucesso){
            return ResponseEntity.ok("Contato excluído com sucesso.");
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir Contato.");
        
    }
    
    @PostMapping("/{id}")
    public String atualizarContato(@ModelAttribute("usuario") @PathVariable Long id, ContatoDto atualizar){
        
        boolean sucesso = contatoService.atualizarContato(id,atualizar);
        
        if(sucesso){
            return "redirect:listausuarios";
        }
        return "redirect:listausuarios?erro";
    }
}


