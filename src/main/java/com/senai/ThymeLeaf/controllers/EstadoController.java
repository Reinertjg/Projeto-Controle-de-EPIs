package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.EstadoDto;
import com.senai.ThymeLeaf.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/estado")
public class EstadoController {
    
    @Autowired
    EstadoService estadoService;
    
    @PostMapping()
    public String cadastraEstado(Model model, @ModelAttribute("estadoDto") EstadoDto dados){
        
        //--chamar o service para cadastrar
        
        boolean retorno = estadoService.cadastrarEstado(dados);
        
        return "redirect:/home";
        
    }
    
}
