package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.design.request.CustomRequest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRequestRepository extends JpaRepository<CustomRequest, Long> {

    boolean existsByCustomerEmail(String email);
}