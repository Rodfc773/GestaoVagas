package br.com.GestaoVagas.repositories.interfaces;

import br.com.GestaoVagas.models.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);
    //Optional<CandidateEntity> findById(UUID id);
}
