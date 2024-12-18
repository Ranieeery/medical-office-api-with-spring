package dev.raniery.med.voll.api.domain.controller;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class DadosAgendamentoConsulta {

    Long idMedico;

    @NotNull
    Long idPaciente;

    @NotNull
    @Future
    LocalDateTime dataConsulta;
}
