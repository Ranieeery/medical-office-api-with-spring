package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.Paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<DadosListaPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return repository.findAll(pageable).map(DadosListaPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizaPaciente dados) {
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);
    }
}
