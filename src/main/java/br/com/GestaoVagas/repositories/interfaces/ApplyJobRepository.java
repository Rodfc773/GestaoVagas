package br.com.GestaoVagas.repositories.interfaces;

import br.com.GestaoVagas.models.ApplyJobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobsEntity, UUID> {
}
