package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.Paciente.CadastroPaciente;
import dev.raniery.med.voll.api.Paciente.Paciente;
import dev.raniery.med.voll.api.Paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid CadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }
}
