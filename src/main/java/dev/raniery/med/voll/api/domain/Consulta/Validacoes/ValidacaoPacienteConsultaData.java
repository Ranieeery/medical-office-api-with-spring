package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.ConsultaRepository;
import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;

import java.time.LocalDateTime;

public class ValidacaoPacienteConsultaData {

    private ConsultaRepository repository;

    public void validarPacienteConsultaData(DadosAgendamentoConsulta dados) {
        LocalDateTime horarioPrimeiraConsulta = dados.data().withHour(7);
        LocalDateTime horarioUltimaConsulta = dados.data().withHour(18);
        boolean pacienteConsultaData = repository.existsByIdAndDataBetween(dados.idPaciente(), horarioPrimeiraConsulta, horarioUltimaConsulta);

        if(pacienteConsultaData) {
            throw new ValidacaoException("Paciente já possui consulta marcada para esta data");
        }
    }
}
