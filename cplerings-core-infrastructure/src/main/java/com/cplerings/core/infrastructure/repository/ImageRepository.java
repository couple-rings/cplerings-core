package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.file.Image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Collection<Image> findAllByIdIn(Collection<Long> imageIds);
}
