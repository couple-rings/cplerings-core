package com.cplerings.core.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.spouse.Spouse;

public interface SpouseRepository extends JpaRepository<Spouse, Long> {

    Optional<Spouse> findByCitizenId(String citizenId);
}
