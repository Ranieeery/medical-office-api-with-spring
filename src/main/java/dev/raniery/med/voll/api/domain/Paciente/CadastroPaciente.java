package dev.raniery.med.voll.api.domain.Paciente;

import dev.raniery.med.voll.api.domain.Dados.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroPaciente (
    @NotBlank
    String nome,

    @NotBlank
    String email,

    @NotBlank
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf,

    @NotNull
    @Valid
    DadosEndereco endereco){

}
