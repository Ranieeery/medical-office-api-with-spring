package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.Medico.CadastroMedico;
import dev.raniery.med.voll.api.Medico.Medico;
import dev.raniery.med.voll.api.Medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid CadastroMedico dados) {
        repository.save(new Medico(dados));
    }
}
