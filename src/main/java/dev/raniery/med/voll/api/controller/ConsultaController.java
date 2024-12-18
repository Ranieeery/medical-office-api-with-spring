package dev.raniery.med.voll.api.controller;

import dev.raniery.med.voll.api.domain.Consulta.AgendaConsultas;
import dev.raniery.med.voll.api.domain.Consulta.DadosDetalhamentoConsulta;
import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final AgendaConsultas agendaConsultas;

    public ConsultaController(AgendaConsultas agendaConsultas) {
        this.agendaConsultas = agendaConsultas;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        agendaConsultas.agendarConsulta(dadosAgendamentoConsulta);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }
}
