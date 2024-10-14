package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.spouse.Spouse;

public interface SpouseRepository extends JpaRepository<Spouse, Long> {
}
