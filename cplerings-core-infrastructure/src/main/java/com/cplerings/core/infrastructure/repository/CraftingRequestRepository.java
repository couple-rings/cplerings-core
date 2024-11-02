package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.crafting.CraftingRequest;

public interface CraftingRequestRepository extends JpaRepository<CraftingRequest, Long> {
}
