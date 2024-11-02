package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.design.DesignVersion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignVersionRepository extends JpaRepository<DesignVersion, Long> {
}
