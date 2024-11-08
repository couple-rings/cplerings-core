package com.cplerings.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cplerings.core.domain.crafting.CraftingStage;

public interface CraftingStageRepository extends JpaRepository<CraftingStage, Long> {
}
