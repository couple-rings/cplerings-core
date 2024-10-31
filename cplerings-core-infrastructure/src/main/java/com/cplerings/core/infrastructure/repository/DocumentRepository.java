package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.file.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
