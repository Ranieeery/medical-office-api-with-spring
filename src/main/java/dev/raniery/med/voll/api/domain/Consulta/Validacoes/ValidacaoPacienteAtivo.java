package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.domain.Paciente.PacienteRepository;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;

public class ValidacaoPacienteAtivo {

    private PacienteRepository repository;

    public void validarPacienteAtivo(DadosAgendamentoConsulta dados) {
        boolean pacienteAtivo = repository.findAtivoById(dados.idPaciente());

        if(!pacienteAtivo) {
            throw new ValidacaoException("Paciente inativo, não é possível agendar consulta");
        }
    }
}
