package dev.raniery.med.voll.api.domain.Consulta;

import dev.raniery.med.voll.api.domain.Medico.Medico;
import dev.raniery.med.voll.api.domain.Medico.MedicoRepository;
import dev.raniery.med.voll.api.domain.Paciente.Paciente;
import dev.raniery.med.voll.api.domain.Paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Paciente paciente = pacienteRepository.findById(dados.idPaciente);
        Medico medico = medicoRepository.findById(dados.idMedico);

        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(dados);
    }
}
