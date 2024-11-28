package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.ring.Ring;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface CompleteCraftingStageDataSource {

    Optional<CraftingStage> findCraftingStageById(Long craftingStageId);

    Optional<Image> findImageById(Long imageId);

    CraftingStage save(CraftingStage craftingStage);

    CustomOrder save(CustomOrder customOrder);

    Set<Document> getMaintenancesByIds(Collection<Long> maintenanceIds);

    Ring save(Ring ring);

    CustomOrderHistory save(CustomOrderHistory customOrderHistory);
}
