package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.jewelry.Jewelry;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
}
