package com.cplerings.core.infrastructure.repository;

import com.cplerings.core.domain.crafting.CraftingStage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CraftingStageRepository extends JpaRepository<CraftingStage, Long> {
}
