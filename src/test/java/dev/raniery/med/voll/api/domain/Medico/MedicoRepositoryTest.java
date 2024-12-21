package dev.raniery.med.voll.api.domain.Medico;

import dev.raniery.med.voll.api.domain.Consulta.Consulta;
import dev.raniery.med.voll.api.domain.Dados.DadosEndereco;
import dev.raniery.med.voll.api.domain.Paciente.CadastroPaciente;
import dev.raniery.med.voll.api.domain.Paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve retornar null se o único médico por especialidade não estiver disponível na data")
    void escolherMedicoAleatorioPorEspecialidade() {
        //Given/Arrange
        var proximaSegunda10Horas = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastrarMedico("Dr. Fulano", "fulano@med.com", "123456", Especialidade.PEDIATRIA);
        var paciente = cadastrarPaciente("Fulano", "paciente@test.com", "12345678901");

        //When/Act
        cadastrarConsulta(medico, paciente, proximaSegunda10Horas);

        //Then/Assert
        var medicoDisponivel = medicoRepository.escolherMedicoPorEspecialidade(Especialidade.PEDIATRIA, proximaSegunda10Horas);
        assertThat(medicoDisponivel).isNull();
    }

    @Test
    @DisplayName("Deve retornar medico quando estiver disponível na data")
    void escolherMedicoDisponivelData() {
        //Given/Arrange
        var proximaSegunda10Horas = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastrarMedico("Dr. Fulano", "fulano@med.com", "123456", Especialidade.PEDIATRIA);

        //When/Act
        var medicoDisponivel = medicoRepository.escolherMedicoPorEspecialidade(Especialidade.PEDIATRIA, proximaSegunda10Horas);

        //Then/Assert
        assertThat(medicoDisponivel).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private CadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new CadastroMedico(
            nome,
            email,
            "61999999999",
            crm,
            especialidade,
            dadosEndereco()
        );
    }

    private CadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new CadastroPaciente(
            nome,
            email,
            "61999999999",
            cpf,
            dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
            "rua xpto",
            "bairro",
            "00000000",
            "Brasilia",
            "DF",
            null,
            null
        );
    }
}