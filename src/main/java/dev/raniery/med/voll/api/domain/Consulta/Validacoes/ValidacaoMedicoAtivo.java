package dev.raniery.med.voll.api.domain.Consulta.Validacoes;

import dev.raniery.med.voll.api.domain.Consulta.DadosAgendamentoConsulta;
import dev.raniery.med.voll.api.domain.Medico.MedicoRepository;
import dev.raniery.med.voll.api.infra.Exception.ValidacaoException;

public class ValidacaoMedicoAtivo {
    private MedicoRepository repository;

    public void validarMedicoAtivo(DadosAgendamentoConsulta dados) {
         if(dados.idMedico() == null) {
             return;
         }

         boolean medicoAtivo = repository.findAtivoById(dados.idMedico());
         if(!medicoAtivo) {
             throw new ValidacaoException("Médico inativo, não é possível agendar consulta");
         }
    }
}
