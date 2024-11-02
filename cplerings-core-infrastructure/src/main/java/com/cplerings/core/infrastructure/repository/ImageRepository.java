package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.file.Image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
