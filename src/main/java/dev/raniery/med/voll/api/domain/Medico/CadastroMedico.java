package dev.raniery.med.voll.api.domain.Medico;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.raniery.med.voll.api.domain.dados.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroMedico(
    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank @Pattern(regexp = "\\d{4,6}")
    String crm,

    @NotNull
    @JsonDeserialize(using = EspecialidadeDeserializer.class) Especialidade especialidade,

    @NotNull @Valid
    DadosEndereco endereco) {

}
