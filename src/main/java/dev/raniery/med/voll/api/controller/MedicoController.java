package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.Medico.CadastroMedico;
import dev.raniery.med.voll.api.Medico.DadosListaMedico;
import dev.raniery.med.voll.api.Medico.Medico;
import dev.raniery.med.voll.api.Medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<DadosListaMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return repository.findAll(pageable).map(DadosListaMedico::new);
    }
}
