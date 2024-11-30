package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.crafting.CraftingStageHistory;

public interface CraftingStageHistoryRepository extends JpaRepository<CraftingStageHistory, Long> {
}
