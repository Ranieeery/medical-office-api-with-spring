package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidacaoHorarioFuncionamento implements ValidacaoAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();

        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesHorarioAbertura = dataConsulta.getHour() < 7;
        boolean depoisHorarioFechamento = dataConsulta.getHour() > 18;

        if (domingo || antesHorarioAbertura || depoisHorarioFechamento) {
            throw new ValidacaoException("Consulta marcada para horário fora do expediente");
        }
    }
}
