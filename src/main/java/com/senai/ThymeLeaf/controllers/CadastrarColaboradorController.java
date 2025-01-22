package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.CadastroColadoradorDto;
import com.senai.ThymeLeaf.dtos.CadastroContatoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping()
public class CadastrarColaboradorController {

    @GetMapping("/cadastrarcolaborador")
    public String exibirListaColaborador(Model model){

        CadastroColadoradorDto cadastroDto = new CadastroColadoradorDto();

        model.addAttribute("CadastroColadoradorDto", cadastroDto);

        return "cadastrarcolaborador";
    }
}
