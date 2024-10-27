package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.design.session.DesignSession;
import com.cplerings.core.domain.design.session.DesignSessionStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DesignSessionRepository extends JpaRepository<DesignSession, Long> {

    boolean existsByCustomerIdAndStatus(Long accountId, DesignSessionStatus status);

    Collection<DesignSession> findAllByCustomerEmail(String email);

    int countByCustomerIdAndStatus(Long accountId, DesignSessionStatus status);
}
