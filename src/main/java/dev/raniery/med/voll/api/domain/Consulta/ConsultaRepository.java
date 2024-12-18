package dev.raniery.med.voll.api.domain.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByIdAndData(Long id, LocalDateTime data);

    boolean existsByIdAndDataBetween(Long id, LocalDateTime dataAfter, LocalDateTime dataBefore);
}
