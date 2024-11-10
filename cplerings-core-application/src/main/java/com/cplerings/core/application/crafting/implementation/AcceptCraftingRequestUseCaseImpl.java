package com.cplerings.core.application.crafting.implementation;

import com.cplerings.core.application.crafting.AcceptCraftingRequestUseCase;
import com.cplerings.core.application.crafting.datasource.AcceptCraftingRequestDataSource;
import com.cplerings.core.application.crafting.error.AcceptCraftingRequestErrorCode;
import com.cplerings.core.application.crafting.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.crafting.mapper.AAcceptCraftingRequestMapper;
import com.cplerings.core.application.crafting.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.entity.craftingrequest.ACraftingRequestStatus;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.price.CalculationTotalPrice;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.branch.Branch;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@UseCaseImplementation
public class AcceptCraftingRequestUseCaseImpl extends AbstractUseCase<AcceptCraftingRequestInput, AcceptCraftingRequestOutput> implements AcceptCraftingRequestUseCase {

    private final AAcceptCraftingRequestMapper aAcceptCraftingRequestMapper;
    private final AcceptCraftingRequestDataSource acceptCraftingRequestDataSource;
    private final CalculationTotalPrice calculateTotalPrice;
    private final ConfigurationService configurationService;

    @Override
    protected void validateInput(UseCaseValidator validator, AcceptCraftingRequestInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.firstCraftingRequestId() > 0, AcceptCraftingRequestErrorCode.CRAFTING_REQUEST_ID_WRONG_POSITIVE_INTEGER);
        validator.validateAndStopExecution(input.secondCraftingRequestId() > 0, AcceptCraftingRequestErrorCode.CRAFTING_REQUEST_ID_WRONG_POSITIVE_INTEGER);
        validator.validateAndStopExecution(input.status() != null, AcceptCraftingRequestErrorCode.STATUS_REQUIRED);
        if (input.status() == ACraftingRequestStatus.REJECTED) {
            validator.validateAndStopExecution(input.firstCommentCrafting() != null, AcceptCraftingRequestErrorCode.COMMENT_REQUIRED);
            validator.validateAndStopExecution(input.secondCommentCrafting() != null, AcceptCraftingRequestErrorCode.COMMENT_REQUIRED);
            validator.validateAndStopExecution(input.branchId() != null, AcceptCraftingRequestErrorCode.BRANCH_ID_REQUIRED);
        }
    }

    @Override
    protected AcceptCraftingRequestOutput internalExecute(UseCaseValidator validator, AcceptCraftingRequestInput input) {
        CraftingRequest firstCraftingRequest = acceptCraftingRequestDataSource.getCraftingRequestById(input.firstCraftingRequestId())
                .orElse(null);
        validator.validateAndStopExecution(firstCraftingRequest != null, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_ID);
        validator.validateAndStopExecution(firstCraftingRequest.getCraftingRequestStatus() == CraftingRequestStatus.PENDING, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_STATUS);
        CraftingRequest secondCraftingRequest = acceptCraftingRequestDataSource.getCraftingRequestById(input.secondCraftingRequestId())
                .orElse(null);
        validator.validateAndStopExecution(secondCraftingRequest != null, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_ID);
        validator.validateAndStopExecution(firstCraftingRequest.getCraftingRequestStatus() == CraftingRequestStatus.PENDING, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_STATUS);
        if (input.status() == ACraftingRequestStatus.ACCEPTED) {
            Branch branch = acceptCraftingRequestDataSource.getBranchById(input.branchId())
                    .orElse(null);
            validator.validateAndStopExecution(branch != null, AcceptCraftingRequestErrorCode.INVALID_BRANCH_ID);
            firstCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.ACCEPTED);
            secondCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.ACCEPTED);
            if (input.firstCommentCrafting() != null)
                firstCraftingRequest.setComment(input.firstCommentCrafting());
            if (input.secondCommentCrafting() != null)
                secondCraftingRequest.setComment(input.secondCommentCrafting());
            List<CraftingRequest> craftingRequests = new ArrayList<>();
            craftingRequests.add(firstCraftingRequest);
            craftingRequests.add(secondCraftingRequest);
            List<CraftingRequest> craftingRequestUpdated = acceptCraftingRequestDataSource.saveCraftingRequests(craftingRequests);
            List<Ring> rings = new ArrayList<>();
            Ring firstRing = Ring.builder()
                    .branch(branch)
                    .status(RingStatus.NOT_AVAIL)
                    .spouse(firstCraftingRequest.getCustomDesign().getSpouse())
                    .purchaseDate(Instant.now())
                    .maintenanceDocument(acceptCraftingRequestDataSource.getMaintenanceDocument())
                    .maintenanceExpiredDate(Instant.now().plus(730, ChronoUnit.DAYS))
                    .build();
            rings.add(firstRing);
            Ring secondRing = Ring.builder()
                    .branch(branch)
                    .status(RingStatus.NOT_AVAIL)
                    .spouse(secondCraftingRequest.getCustomDesign().getSpouse())
                    .purchaseDate(Instant.now())
                    .maintenanceDocument(acceptCraftingRequestDataSource.getMaintenanceDocument())
                    .maintenanceExpiredDate(Instant.now().plus(730, ChronoUnit.DAYS))
                    .build();
            rings.add(secondRing);
            List<Ring> ringsCreated = acceptCraftingRequestDataSource.saveRings(rings);
            Contract contract = Contract.builder()
                    .build();
            Contract contractCreated = acceptCraftingRequestDataSource.saveContract(contract);
            Configuration configuration = acceptCraftingRequestDataSource.getConfigurationForSideDiamond();
            Double sideDiamondPrice = Double.parseDouble(configuration.getValue());
            Money firstRingPrice = calculateTotalPrice.calculationTotalPrice(firstCraftingRequest.getMetalSpecification().getPricePerUnit(), firstCraftingRequest.getDiamondSpecification().getPrice(), firstCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue(), firstCraftingRequest.getCustomDesign().getSideDiamondsCount(), sideDiamondPrice);
            Money secondRingPrice = calculateTotalPrice.calculationTotalPrice(secondCraftingRequest.getMetalSpecification().getPricePerUnit(), secondCraftingRequest.getDiamondSpecification().getPrice(), secondCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue(), secondCraftingRequest.getCustomDesign().getSideDiamondsCount(), sideDiamondPrice);
            Money totalPrice = Money.create(firstRingPrice.getAmount().add(secondRingPrice.getAmount()));
            CustomOrder customOrder = CustomOrder.builder()
                    .customer(firstCraftingRequest.getCustomer())
                    .firstRing(ringsCreated.get(0))
                    .secondRing(ringsCreated.get(1))
                    .contract(contractCreated)
                    .status(CustomOrderStatus.PENDING)
                    .totalPrice(totalPrice)
                    .build();
            CustomOrder customOrderCreated = acceptCraftingRequestDataSource.saveCustomOrder(customOrder);
            CraftingStage firstStage = CraftingStage.builder()
                    .status(CraftingStageStatus.PENDING)
                    .customOrder(customOrderCreated)
                    .progress(configurationService.getCraftingStageProgress1())
                    .name("Stage 1")
                    .build();
            CraftingStage secondStage = CraftingStage.builder()
                    .status(CraftingStageStatus.PENDING)
                    .customOrder(customOrderCreated)
                    .progress(configurationService.getCraftingStageProgress2())
                    .name("Stage 2")
                    .build();
            CraftingStage thirdStage = CraftingStage.builder()
                    .status(CraftingStageStatus.PENDING)
                    .customOrder(customOrderCreated)
                    .progress(configurationService.getCraftingStageProgress3())
                    .name("Stage 3")
                    .build();
            List<CraftingStage> craftingStages = new ArrayList<>();
            craftingStages.add(firstStage);
            craftingStages.add(secondStage);
            craftingStages.add(thirdStage);
            acceptCraftingRequestDataSource.saveStages(craftingStages);
            return aAcceptCraftingRequestMapper.toOutput(customOrderCreated, craftingRequestUpdated.get(0), craftingRequestUpdated.get(1));
        }
        if (input.status() == ACraftingRequestStatus.REJECTED) {
            firstCraftingRequest.setComment(input.firstCommentCrafting());
            secondCraftingRequest.setComment(input.secondCommentCrafting());
            List<CraftingRequest> craftingRequests = new ArrayList<>();
            craftingRequests.add(firstCraftingRequest);
            craftingRequests.add(secondCraftingRequest);
            List<CraftingRequest> craftingRequestUpdated = acceptCraftingRequestDataSource.saveCraftingRequests(craftingRequests);
            return aAcceptCraftingRequestMapper.toOutput(null, craftingRequestUpdated.get(0), craftingRequestUpdated.get(0));
        }
        validator.validateAndStopExecution(false, AcceptCraftingRequestErrorCode.WRONG_STATUS);
        return null;
    }
}
