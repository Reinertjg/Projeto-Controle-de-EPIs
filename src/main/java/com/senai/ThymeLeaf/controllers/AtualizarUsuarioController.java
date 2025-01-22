
package com.senai.ThymeLeaf.controllers;

import com.senai.ThymeLeaf.dtos.AtualizarUsuarioDto;
import com.senai.ThymeLeaf.dtos.UsuarioDto;
import com.senai.ThymeLeaf.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("atualizarusuario")
public class AtualizarUsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/{id}")
    public String atualizarUsuario (Model model, @PathVariable Long id){

        UsuarioDto usuarioDto = usuarioService.obterUsuario(id);

        AtualizarUsuarioDto atualizarUsuario = new AtualizarUsuarioDto();
        atualizarUsuario.setId(usuarioDto.getId());
        atualizarUsuario.setNome(usuarioDto.getNome());
        atualizarUsuario.setEmail(usuarioDto.getEmail());

        model.addAttribute("atualizarUsuarioDto", atualizarUsuario);

        return "atualizarusuario";
    }
}
