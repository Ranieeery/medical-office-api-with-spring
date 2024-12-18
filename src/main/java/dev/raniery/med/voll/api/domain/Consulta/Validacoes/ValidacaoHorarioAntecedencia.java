package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacaoHorarioAntecedencia {
    public void validarHorarioAntecedencia(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime dataAtual = LocalDateTime.now();
        long diferencaData = Duration.between(dataAtual, dataConsulta).toMinutes();

        if (diferencaData < 30) {
            throw new ValidacaoException("Consulta deve ser marcada com pelo menos 30 minutos de antecedência");
        }
    }
}
