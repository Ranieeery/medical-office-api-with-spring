package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.ConsultaRepository;
import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoConsultaHorario implements ValidacaoAgendamentoConsulta {

    private final ConsultaRepository repository;

    public ValidacaoMedicoConsultaHorario(ConsultaRepository repository) {
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        boolean medicoConsultaHorario = repository.existsByIdAndData(dados.idMedico(), dados.data());

        if (medicoConsultaHorario) {
            throw new ValidacaoException("Médico já possui consulta marcada para o horário");
        }
    }
}
