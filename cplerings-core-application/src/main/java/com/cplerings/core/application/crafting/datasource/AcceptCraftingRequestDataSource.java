package com.cplerings.core.application.crafting.datasource;

import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageHistory;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestHistory;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestHistory;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.ring.RingHistory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AcceptCraftingRequestDataSource {

    Optional<CraftingRequest> getCraftingRequestById(Long craftingRequestId);

    List<Ring> saveRings(List<Ring> rings);

    Document getMaintenanceDocument();

    Contract saveContract(Contract contract);

    CustomOrder saveCustomOrder(CustomOrder customOrder);

    List<CraftingRequest> saveCraftingRequests(List<CraftingRequest> craftingRequests);

    Optional<Branch> getBranchById(Long branchId);

    Configuration getConfigurationForSideDiamond();

    List<CraftingStage> saveStages(List<CraftingStage> craftingStages);

    void save(CustomDesign customDesign);

    Ring save(Ring ring);

    Collection<Diamond> getUnusedDiamondsFromSpecsAndBranch(Collection<Long> diamondSpecIds, Long branchId);

    Optional<Diamond> getUnusedDiamondFromSpecAndBranch(Long diamondSpecId, Long branchId);

    RingDiamond save(RingDiamond ringDiamond);

    Diamond save(Diamond diamond);

    CustomRequest save(CustomRequest customRequest);

    CustomRequestHistory save(CustomRequestHistory customRequestHistory);

    CraftingRequestHistory save(CraftingRequestHistory craftingRequestHistory);

    CustomOrderHistory save(CustomOrderHistory customOrderHistory);

    RingHistory save(RingHistory ringHistory);

    CraftingStageHistory save(CraftingStageHistory craftingStageHistory);
}
