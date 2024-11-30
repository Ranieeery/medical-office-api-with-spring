package dev.raniery.med.voll.api.domain.Paciente;

import dev.raniery.med.voll.api.domain.dados.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record AtualizaPaciente(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {
}
