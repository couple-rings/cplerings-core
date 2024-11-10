package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;

import java.util.Optional;

public interface CompleteCraftingStageDataSource {

    Optional<CraftingStage> findCraftingStageById(Long craftingStageId);

    Optional<Image> findImageById(Long imageId);

    CraftingStage save(CraftingStage craftingStage);

    CustomOrder save(CustomOrder customOrder);
}
