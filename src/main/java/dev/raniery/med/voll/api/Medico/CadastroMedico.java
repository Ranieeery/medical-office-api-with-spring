package dev.raniery.med.voll.api.Medico;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.raniery.med.voll.api.dados.Endereco;

public record CadastroMedico(String nome, String email, String crm, @JsonDeserialize(using = EspecialidadeDeserializer.class) Especialidade especialidade, Endereco endereco) {

}
