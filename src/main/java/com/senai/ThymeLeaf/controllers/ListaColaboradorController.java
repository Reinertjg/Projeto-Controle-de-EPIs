package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ListaColaboradorController {
    
    @Autowired
    ColaboradorService colaboradorService;
    
    @GetMapping("/listacolaboradores")
    public String exibirListaColaboradores(Model model){
                     
        model.addAttribute("colaboradores",colaboradorService.obterListaColaboradores());
        
        //--template : retorna o nome do arquivo html localizado lá na pasta templates.
        return "listacolaboradores";
    }
    
}
