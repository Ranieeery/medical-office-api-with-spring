package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.ConsultaRepository;
import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;

public class ValidacaoMedicoConsultaHorario {

    private ConsultaRepository repository;

    public void validarMedicoConsultaHorario(DadosAgendamentoConsulta dados) {
        boolean medicoConsultaHorario = repository.existsByIdAndData(dados.idMedico(), dados.data());

        if(medicoConsultaHorario) {
            throw new ValidacaoException("Médico já possui consulta marcada para o horário");
        }
    }
}
