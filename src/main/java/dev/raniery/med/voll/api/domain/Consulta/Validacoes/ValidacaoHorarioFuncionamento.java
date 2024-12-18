package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidacaoHorarioFuncionamento {

    public void validarHorarioFuncionamento(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();

        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesHorarioAbertura = dataConsulta.getHour() < 7;
        boolean depoisHorarioFechamento = dataConsulta.getHour() > 18;

        if (domingo || antesHorarioAbertura || depoisHorarioFechamento) {
            throw new ValidacaoException("Consulta marcada para horário fora do expediente");
        }
    }
}
