package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.design.crafting.CraftingRequest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CraftingRequestRepository extends JpaRepository<CraftingRequest, Long> {
}
