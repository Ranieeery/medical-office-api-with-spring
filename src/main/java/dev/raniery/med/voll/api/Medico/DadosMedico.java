package dev.raniery.med.voll.api.Medico;

import dev.raniery.med.voll.api.dados.Endereco;

public record DadosMedico (
    Long id,
    String nome,
    String email,
    String crm,
    Especialidade especialidade,
    Endereco endereco) {

    public DadosMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
