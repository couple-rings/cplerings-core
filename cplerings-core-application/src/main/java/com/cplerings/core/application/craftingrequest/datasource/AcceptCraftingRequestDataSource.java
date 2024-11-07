package com.cplerings.core.application.craftingrequest.datasource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.spouse.Spouse;

public interface AcceptCraftingRequestDataSource {

    Optional<CraftingRequest> getCraftingRequestById(Long craftingRequestId);
    List<Ring> saveRings(List<Ring> rings);
    Document getMaintenanceDocument();
    Contract saveContract(Contract contract);
    CustomOrder saveCustomOrder(CustomOrder customOrder);
    List<CraftingRequest> saveCraftingRequests(List<CraftingRequest> craftingRequests);
    Optional<Branch> getBranchById(Long branchId);
    Configuration getConfigurationForSideDiamond();
    void saveStages(List<CraftingStage> craftingStages);
    void updateRingWithCustomOrder(Long ringId, CustomOrder customOrder);
}
