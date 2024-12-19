package dev.raniery.med.voll.api.domain.Paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable pageable);

    @Query("""
        SELECT p.ativo FROM Paciente p
        WHERE p.id = :id
        """)
    boolean findAtivoById(Long id);
}
