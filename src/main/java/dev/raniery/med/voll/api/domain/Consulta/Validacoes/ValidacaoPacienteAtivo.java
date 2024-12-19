package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.domain.Paciente.PacienteRepository;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteAtivo implements ValidacaoAgendamentoConsulta {

    private final PacienteRepository repository;

    public ValidacaoPacienteAtivo(PacienteRepository repository) {
        this.repository = repository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        boolean pacienteAtivo = repository.findAtivoById(dados.idPaciente());

        if (!pacienteAtivo) {
            throw new ValidacaoException("Paciente inativo, não é possível agendar consulta");
        }
    }
}
