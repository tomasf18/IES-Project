package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import sts.backend.core_app.models.RegistrationCode;

@Repository
public interface RegistrationCodeRepository extends JpaRepository<RegistrationCode, Long> {
    Optional<RegistrationCode> findByCode(String code);
}
    