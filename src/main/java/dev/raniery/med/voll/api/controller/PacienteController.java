package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.domain.Paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid CadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DadosListaPaciente>>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable, PagedResourcesAssembler<DadosListaPaciente> assembler) {
        var pacientes = repository.findAllByAtivoTrue(pageable).map(DadosListaPaciente::new);

        return ResponseEntity.ok(assembler.toModel(pacientes));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizaPaciente dados) {
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);

        return ResponseEntity.ok(new DadosPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosPaciente> detalhar(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosPaciente(paciente));
    }
}
