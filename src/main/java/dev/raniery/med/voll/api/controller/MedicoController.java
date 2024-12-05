package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.domain.Medico.*;
import dev.raniery.med.voll.api.infra.Security.DadosTokenJWT;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMedico> cadastro(@RequestBody @Valid CadastroMedico dados, UriComponentsBuilder uriBuilder) {
        Medico medico = new Medico(dados);
        repository.save(medico);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosMedico(medico));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DadosListaMedico>>> listar(@PageableDefault(sort = {"nome"}) Pageable pageable, PagedResourcesAssembler<DadosListaMedico> assembler) {
        var medicos = repository.findAllByAtivoTrue(pageable).map(DadosListaMedico::new);

        return ResponseEntity.ok(assembler.toModel(medicos));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosMedico> atualizar(@RequestBody @Valid AtualizaMedico dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarMedico(dados);

        return ResponseEntity.ok(new DadosMedico(medico));
    }

    //  Exclusão lógica
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosTokenJWT> excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosMedico> detalhar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosMedico(medico));
    }
}
