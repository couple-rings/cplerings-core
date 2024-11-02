package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.file.Document;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
