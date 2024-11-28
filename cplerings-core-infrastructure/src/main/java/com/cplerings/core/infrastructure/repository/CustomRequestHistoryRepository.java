package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.request.CustomRequestHistory;

public interface CustomRequestHistoryRepository extends JpaRepository<CustomRequestHistory, Long> {
}
