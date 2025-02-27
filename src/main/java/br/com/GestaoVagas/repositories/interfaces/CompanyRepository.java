package br.com.GestaoVagas.repositories.interfaces;

import br.com.GestaoVagas.models.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByNameOrEmail(String name, String email);
    Optional<CompanyEntity> findByName(String name);
}
