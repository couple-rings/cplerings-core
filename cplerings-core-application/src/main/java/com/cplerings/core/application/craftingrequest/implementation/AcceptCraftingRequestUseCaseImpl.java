package com.cplerings.core.application.craftingrequest.implementation;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.cplerings.core.application.craftingrequest.AcceptCraftingRequestUseCase;
import com.cplerings.core.application.craftingrequest.datasource.AcceptCraftingRequestDataSource;
import com.cplerings.core.application.craftingrequest.error.AcceptCraftingRequestErrorCode;
import com.cplerings.core.application.craftingrequest.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.craftingrequest.mapper.AAcceptCraftingRequestMapper;
import com.cplerings.core.application.craftingrequest.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.entity.craftingrequest.ACraftingRequestStatus;
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

@Slf4j
@RequiredArgsConstructor
@UseCaseImplementation
public class AcceptCraftingRequestUseCaseImpl extends AbstractUseCase<AcceptCraftingRequestInput, AcceptCraftingRequestOutput> implements AcceptCraftingRequestUseCase {

    private final AAcceptCraftingRequestMapper aAcceptCraftingRequestMapper;
    private final AcceptCraftingRequestDataSource acceptCraftingRequestDataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, AcceptCraftingRequestInput input) {
        super.validateInput(validator, input);
        if (input != null) {
            validator.validateAndStopExecution(input.firstCraftingRequestId() > 0, AcceptCraftingRequestErrorCode.CRAFTING_REQUEST_ID_WRONG_POSITIVE_INTEGER);
            validator.validateAndStopExecution(input.secondCraftingRequestId() > 0, AcceptCraftingRequestErrorCode.CRAFTING_REQUEST_ID_WRONG_POSITIVE_INTEGER);
            validator.validateAndStopExecution(input.status() != null, AcceptCraftingRequestErrorCode.STATUS_REQUIRED);
            if (input.status().equals(ACraftingRequestStatus.REJECTED)) {
                validator.validateAndStopExecution(input.firstCommentCrafting() != null, AcceptCraftingRequestErrorCode.COMMENT_REQUIRED);
                validator.validateAndStopExecution(input.secondCommentCrafting() != null, AcceptCraftingRequestErrorCode.COMMENT_REQUIRED);
            }
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
            List<CraftingRequest> craftingRequests = new ArrayList<>();
            craftingRequests.add(firstCraftingRequest);
            craftingRequests.add(secondCraftingRequest);
            List<CraftingRequest> craftingRequestUpdated = acceptCraftingRequestDataSource.saveCraftingRequests(craftingRequests);
            List<Ring> rings = new ArrayList<>();
            Ring firstRing = Ring.builder()
                    .branch(branch)
                    .status(RingStatus.NOT_AVAILABLE)
                    .spouse(firstCraftingRequest.getCustomDesign().getSpouse())
                    .purchaseDate(Instant.now())
                    .maintenanceDocument(acceptCraftingRequestDataSource.getMaintenanceDocument())
                    .maintenanceExpiredDate(Instant.now().plus(730, ChronoUnit.DAYS))
                    .build();
            rings.add(firstRing);
            Ring secondRing = Ring.builder()
                    .branch(branch)
                    .status(RingStatus.NOT_AVAILABLE)
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
            BigDecimal totalPrice = calculateTotalPrice(firstCraftingRequest, secondCraftingRequest, sideDiamondPrice);
            CustomOrder customOrder = CustomOrder.builder()
                    .customer(firstCraftingRequest.getCustomer())
                    .firstRing(ringsCreated.get(0))
                    .secondRing(ringsCreated.get(1))
                    .contract(contractCreated)
                    .status(CustomOrderStatus.PENDING)
                    .totalPrice(Money.create(totalPrice))
                    .build();
            CustomOrder customOrderCreated = acceptCraftingRequestDataSource.saveCustomOrder(customOrder);
            CraftingStage firstStage = CraftingStage.builder()
                    .craftingStageStatus(CraftingStageStatus.PENDING)
                    .customOrder(customOrderCreated)
                    .progress(50)
                    .name("s1")
                    .build();
            CraftingStage secondStage = CraftingStage.builder()
                    .craftingStageStatus(CraftingStageStatus.PENDING)
                    .customOrder(customOrderCreated)
                    .progress(75)
                    .name("s2")
                    .build();
            CraftingStage thirdStage = CraftingStage.builder()
                    .craftingStageStatus(CraftingStageStatus.PENDING)
                    .customOrder(customOrderCreated)
                    .progress(100)
                    .name("s3")
                    .build();
            List<CraftingStage> craftingStages = new ArrayList<>();
            craftingStages.add(firstStage);
            craftingStages.add(secondStage);
            craftingStages.add(thirdStage);
            acceptCraftingRequestDataSource.saveStages(craftingStages);
            acceptCraftingRequestDataSource.updateRingWithCustomOrder(ringsCreated.get(0).getId(), customOrderCreated);
            acceptCraftingRequestDataSource.updateRingWithCustomOrder(ringsCreated.get(1).getId(), customOrderCreated);
            return aAcceptCraftingRequestMapper.toOutput(customOrderCreated, craftingRequestUpdated.get(0), craftingRequestUpdated.get(1));
        } else if (input.status() == ACraftingRequestStatus.REJECTED) {
            firstCraftingRequest.setComment(input.firstCommentCrafting());
            secondCraftingRequest.setComment(input.secondCommentCrafting());
            List<CraftingRequest> craftingRequests = new ArrayList<>();
            craftingRequests.add(firstCraftingRequest);
            craftingRequests.add(secondCraftingRequest);
            List<CraftingRequest> craftingRequestUpdated = acceptCraftingRequestDataSource.saveCraftingRequests(craftingRequests);
            return aAcceptCraftingRequestMapper.toOutput(null, craftingRequestUpdated.get(0), craftingRequestUpdated.get(0));
        } else {
            validator.validateAndStopExecution(input.status() == ACraftingRequestStatus.ACCEPTED || input.status() == ACraftingRequestStatus.REJECTED, AcceptCraftingRequestErrorCode.WRONG_STATUS);
            return null;
        }
    }

    private BigDecimal calculateTotalPrice(CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest, Double sideDiamondPrice) {
        BigDecimal firstRingPrice = (firstCraftingRequest.getMetalSpecification().getPricePerUnit()
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(firstCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue())
                .add(firstCraftingRequest.getDiamondSpecification().getPrice().getAmount())
                .add(BigDecimal.valueOf(firstCraftingRequest.getCustomDesign().getSideDiamondsCount())
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(1.3));
        BigDecimal secondRingPrice = (secondCraftingRequest.getMetalSpecification().getPricePerUnit()
                .getAmount()
                .multiply(BigDecimal.valueOf(3.75))
                .multiply(secondCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue())
                .add(secondCraftingRequest.getDiamondSpecification().getPrice().getAmount())
                .add(BigDecimal.valueOf(secondCraftingRequest.getCustomDesign().getSideDiamondsCount())
                        .multiply(BigDecimal.valueOf(sideDiamondPrice))))
                .multiply(BigDecimal.valueOf(1.3));
        return firstRingPrice.add(secondRingPrice);
    }
}
