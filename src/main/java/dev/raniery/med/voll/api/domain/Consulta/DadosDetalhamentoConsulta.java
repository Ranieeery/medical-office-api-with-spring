package dev.raniery.med.voll.api.domain.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime dataConsulta) {
}
