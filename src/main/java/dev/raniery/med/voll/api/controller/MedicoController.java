package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.Medico.CadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {



    @PostMapping
    public void cadastro(@RequestBody CadastroMedico dados) {
        System.out.println(dados);
    }
}
