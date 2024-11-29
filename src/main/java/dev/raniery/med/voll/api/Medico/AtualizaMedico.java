package dev.raniery.med.voll.api.Medico;

import dev.raniery.med.voll.api.dados.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record AtualizaMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco) {
}
