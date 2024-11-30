package dev.raniery.med.voll.api.domain.Paciente;

public record DadosListaPaciente(
    Long id,
    String nome,
    String email,
    String cpf) {

    public DadosListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
