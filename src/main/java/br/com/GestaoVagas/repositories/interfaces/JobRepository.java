package br.com.GestaoVagas.repositories.interfaces;

import br.com.GestaoVagas.models.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    Optional<JobEntity> findByDescription(String description);

    List<JobEntity> findByDescriptionContaining(String filter);
}
