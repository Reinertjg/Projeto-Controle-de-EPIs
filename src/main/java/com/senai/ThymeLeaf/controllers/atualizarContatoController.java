package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.CadastroDto;
import com.senai.ThymeLeaf.dtos.ContatoDto;
import com.senai.ThymeLeaf.dtos.UsuarioDto;
import com.senai.ThymeLeaf.services.ContatoService;
import com.senai.ThymeLeaf.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atualizarcontato")
public class atualizarContatoController {
    
    @Autowired
    ContatoService contatoService;
    
    @GetMapping("/{id}")
    public String atualizarUsuario(Model model, @PathVariable Long id){               
        
         ContatoDto contato = contatoService.obterContato(id);
                
        model.addAttribute("contato", contato);
        
        if (contato.getId() > 0){
            return "atualizarcontato";
        }
        
        return "redirect:/listacontato";
    }
        
    

}

