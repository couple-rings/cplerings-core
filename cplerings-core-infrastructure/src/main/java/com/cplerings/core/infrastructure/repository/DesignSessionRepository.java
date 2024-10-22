package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.DesignSession;

public interface DesignSessionRepository extends JpaRepository<DesignSession, Long> {
}
