package dev.raniery.med.voll.api.domain.Medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
        SELECT m.ativo FROM Medico m
        WHERE m.id = :id
        """)
    boolean findAtivoById(Long id);

    @Query("""
        SELECT m FROM Medico m
        WHERE m.ativo = true AND m.especialidade = :especialidade
        AND m.id NOT IN (SELECT c.medico.id FROM Consulta c WHERE c.data = :data)
        ORDER BY rand()
        LIMIT 1
        """)
    Medico escolherMedicoPorEspecialidade(Especialidade especialidade, @NotNull @Future LocalDateTime data);

}
