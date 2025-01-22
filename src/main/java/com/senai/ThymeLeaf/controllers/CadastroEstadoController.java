package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.EstadoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastroestado")
public class CadastroEstadoController {
 
    @GetMapping
    public String exibirCadastroEstado(Model model){
        
        EstadoDto estadoDto = new EstadoDto();
        model.addAttribute("estadoDto", estadoDto);
        
        return "cadastroestado";
    }
    
}
