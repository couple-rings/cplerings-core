package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.order.status.TransportationNote;

public interface TransportationNoteRepository extends JpaRepository<TransportationNote, Long> {
}
