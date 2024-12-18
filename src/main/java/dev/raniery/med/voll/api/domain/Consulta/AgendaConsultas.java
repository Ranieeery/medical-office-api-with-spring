package dev.raniery.med.voll.api.domain.Consulta;

import dev.raniery.med.voll.api.domain.Medico.Medico;
import dev.raniery.med.voll.api.domain.Medico.MedicoRepository;
import dev.raniery.med.voll.api.domain.Paciente.Paciente;
import dev.raniery.med.voll.api.domain.Paciente.PacienteRepository;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultas {

    private final ConsultaRepository consultaRepository;

    private final MedicoRepository medicoRepository;

    private final PacienteRepository pacienteRepository;

    public AgendaConsultas(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public void agendarConsulta(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("ID informado não corresponde a um paciente existente");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID informado não corresponde a um médico existente");
        }

        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Medico medico = escolherMedico(dados);
        Consulta consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando não é informado o ID do médico");
        }

        return medicoRepository.escolherMedicoPorEspecialidade(dados.especialidade(), dados.data());
    }
}
