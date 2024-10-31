package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.DesignVersion;

public interface DesignVersionRepository extends JpaRepository<DesignVersion, Long> {
}
