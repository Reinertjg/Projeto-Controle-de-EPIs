package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.CadastroContatoDto;
import com.senai.ThymeLeaf.dtos.CadastroDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class CadastrarContatoController {
    
    @GetMapping("/cadastrarcontato")
    public String exibirListaContato(Model model){
        
        CadastroContatoDto cadastroDto = new CadastroContatoDto();
        
        model.addAttribute("contatoDto", cadastroDto);
        
        return "cadastrarcontato";
    }
}
