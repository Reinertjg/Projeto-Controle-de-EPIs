package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.ColaboradorDto;
import com.senai.ThymeLeaf.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visualizarcolaborador")
public class VisualizarColaboradorController {
    
    @Autowired
    ColaboradorService colaboradorService;
    
    @GetMapping("/{id}")
    public String exibirVisualizarColaborador(Model model, @PathVariable Long id){

        ColaboradorDto colaborador = colaboradorService.obterColaborador(id);
                
        model.addAttribute("colaboradorDto", colaborador);
        
        if (colaborador.getId() > 0){
            return "visualizarcolaborador";
        }
        
        return "redirect:/listacolaboradores";
        
    }
    
}
