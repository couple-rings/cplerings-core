package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.spouse.Spouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface SpouseRepository extends JpaRepository<Spouse, Long> {

    Optional<Spouse> findByCitizenId(String citizenId);

    boolean existsByCitizenIdIn(Collection<String> citizenIds);
}
