package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.design.crafting.CraftingRequestHistory;

public interface CraftingRequestHistoryRepository extends JpaRepository<CraftingRequestHistory, Long> {
}
