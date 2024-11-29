package dev.raniery.med.voll.api.Paciente;

public record DadosListaPaciente(
    String nome,
    String email,
    String cpf) {

    public DadosListaPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
