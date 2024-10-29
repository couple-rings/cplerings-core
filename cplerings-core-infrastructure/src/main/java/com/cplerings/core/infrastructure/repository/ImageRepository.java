package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.file.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
