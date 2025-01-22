package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.AtualizarUsuarioDto;
import com.senai.ThymeLeaf.dtos.CadastroDto;
import com.senai.ThymeLeaf.dtos.ContatoDto;
import com.senai.ThymeLeaf.exceptions.UsuarioException;
import com.senai.ThymeLeaf.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @PostMapping()
    public String cadastrarUsuario(@ModelAttribute("usuario") CadastroDto cadastro, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.cadastrarUsuario(cadastro);
            return "redirect:/listausuarios";
        } catch (UsuarioException.EmailJaCadastradoException | UsuarioException.SenhaInvalidaException ex) {
            // Passa a mensagem de erro para o HTML
            redirectAttributes.addFlashAttribute("erro", ex.getMessage());
            return "redirect:/cadastrarusuario";
        }
    }


    @PostMapping("/{id}")
    public String atualizarUsuario(@ModelAttribute("usuario") @PathVariable Long id, AtualizarUsuarioDto atualizar, RedirectAttributes redirectAttributes){

        try {
            usuarioService.atualizarUsuario(atualizar, id);
            return "redirect:/listausuarios";
        } catch (UsuarioException.EmailJaCadastradoException | UsuarioException.SenhaInvalidaException ex) {
            // Passa a mensagem de erro para o HTML
            redirectAttributes.addFlashAttribute("erro", ex.getMessage());
            return "redirect:/atualizarusuario/{id}";
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String>  excluirUsuario(@PathVariable Long id){
    
        boolean sucesso = usuarioService.excluirUsuario(id);
        
        if (sucesso){
            return ResponseEntity.ok("Usuário excluído com sucesso.");
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário.");
        
    }
}
