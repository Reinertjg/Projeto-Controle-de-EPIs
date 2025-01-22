package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.CadastroContatoDto;
import com.senai.ThymeLeaf.dtos.ContatoDto;
import com.senai.ThymeLeaf.dtos.UsuarioDto;
import com.senai.ThymeLeaf.services.ContatoService;
import com.senai.ThymeLeaf.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visualizarcontato")
public class VizualizarContatoController {
    
    @Autowired
    ContatoService contatoService;
    
    @GetMapping("/{id}")
    public String exibirVisualizarContato(Model model, @PathVariable Long id){

        ContatoDto usuario = contatoService.obterContato(id);
                
        model.addAttribute("contatoDto", usuario);
        
        if (usuario.getId() > 0){
            return "visualizarcontato";
        }
        
        return "redirect:/listacontato";
        
    }
    
}
