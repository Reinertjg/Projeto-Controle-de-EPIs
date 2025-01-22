package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.AtualizarUsuarioDto;
import com.senai.ThymeLeaf.dtos.CadastroColadoradorDto;
import com.senai.ThymeLeaf.dtos.ColaboradorDto;
import com.senai.ThymeLeaf.exceptions.ColaboradorException;
import com.senai.ThymeLeaf.exceptions.UsuarioException;
import com.senai.ThymeLeaf.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {

    @Autowired
    ColaboradorService colaboradorService;

    @PostMapping()
    public String cadastrarColaborador(@ModelAttribute("colaborador") CadastroColadoradorDto cadastro, RedirectAttributes redirectAttributes) {
        try {
            colaboradorService.cadastrarColadorador(cadastro);
            return "redirect:/listacolaboradores";
        } catch (ColaboradorException.EmailJaCadastradoException | ColaboradorException.DataInvalidaException ex) {
            // Passa a mensagem de erro para o HTML
            redirectAttributes.addFlashAttribute("erro", ex.getMessage());
            return "redirect:/cadastrarcolaborador";
        }
    }

    @PostMapping("/{id}")
    public String atualizarColaborador(@ModelAttribute("colaborador") @PathVariable Long id, ColaboradorDto atualizar, RedirectAttributes redirectAttributes){

        try {
            colaboradorService.atualizarColaborador(atualizar, id);
            return "redirect:/listacolaboradores";
        } catch (ColaboradorException.EmailJaCadastradoException | ColaboradorException.DataInvalidaException ex) {
            // Passa a mensagem de erro para o HTML
            redirectAttributes.addFlashAttribute("erro", ex.getMessage());
            return "redirect:/atualizarcolaborador/{id}";
        }
    }

}
