package com.cplerings.core.application.crafting.implementation;

import com.cplerings.core.application.crafting.AcceptCraftingRequestUseCase;
import com.cplerings.core.application.crafting.datasource.AcceptCraftingRequestDataSource;
import com.cplerings.core.application.crafting.error.AcceptCraftingRequestErrorCode;
import com.cplerings.core.application.crafting.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.crafting.mapper.AAcceptCraftingRequestMapper;
import com.cplerings.core.application.crafting.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.price.CalculationTotalPriceService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.configuration.Configuration;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@UseCaseImplementation
public class AcceptCraftingRequestUseCaseImpl extends AbstractUseCase<AcceptCraftingRequestInput, AcceptCraftingRequestOutput> implements AcceptCraftingRequestUseCase {

    private final AAcceptCraftingRequestMapper mapper;
    private final AcceptCraftingRequestDataSource dataSource;
    private final CalculationTotalPriceService calculationTotalPriceService;
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
        }
    }

    @Override
    protected AcceptCraftingRequestOutput internalExecute(UseCaseValidator validator, AcceptCraftingRequestInput input) {
        CraftingRequest firstCraftingRequest = dataSource.getCraftingRequestById(input.firstCraftingRequestId())
                .orElse(null);
        validator.validateAndStopExecution(firstCraftingRequest != null, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_ID);
        validator.validateAndStopExecution(firstCraftingRequest.getCraftingRequestStatus() == CraftingRequestStatus.PENDING, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_STATUS);
        CraftingRequest secondCraftingRequest = dataSource.getCraftingRequestById(input.secondCraftingRequestId())
                .orElse(null);
        validator.validateAndStopExecution(secondCraftingRequest != null, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_ID);
        validator.validateAndStopExecution(secondCraftingRequest.getCraftingRequestStatus() == CraftingRequestStatus.PENDING, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_STATUS);

        if (input.status() == ACraftingRequestStatus.ACCEPTED) {
            List<CraftingRequest> craftingRequests = acceptCraftingRequests(input, firstCraftingRequest, secondCraftingRequest);

            List<Ring> rings = createRings(validator, firstCraftingRequest, secondCraftingRequest);

            Contract contract = createContract();

            CustomOrder customOrder = createCustomOrder(firstCraftingRequest, secondCraftingRequest, rings, contract);

            createCraftingStages(customOrder);

            completeCustomRequest(firstCraftingRequest);

            disableCustomDesigns(firstCraftingRequest, secondCraftingRequest);

            return mapper.toOutput(customOrder, craftingRequests.get(0), craftingRequests.get(1));
        }

        if (input.status() == ACraftingRequestStatus.REJECTED) {
            firstCraftingRequest.setComment(input.firstCommentCrafting());
            secondCraftingRequest.setComment(input.secondCommentCrafting());
            List<CraftingRequest> craftingRequests = new ArrayList<>();
            craftingRequests.add(firstCraftingRequest);
            craftingRequests.add(secondCraftingRequest);
            List<CraftingRequest> craftingRequestUpdated = dataSource.saveCraftingRequests(craftingRequests);
            return mapper.toOutput(null, craftingRequestUpdated.get(0), craftingRequestUpdated.get(0));
        }

        validator.validateAndStopExecution(false, AcceptCraftingRequestErrorCode.WRONG_STATUS);
        return null;
    }

    private List<CraftingRequest> acceptCraftingRequests(AcceptCraftingRequestInput input, CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest) {
        firstCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.ACCEPTED);
        secondCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.ACCEPTED);
        if (input.firstCommentCrafting() != null)
            firstCraftingRequest.setComment(input.firstCommentCrafting());
        if (input.secondCommentCrafting() != null)
            secondCraftingRequest.setComment(input.secondCommentCrafting());
        List<CraftingRequest> craftingRequests = new ArrayList<>();
        craftingRequests.add(firstCraftingRequest);
        craftingRequests.add(secondCraftingRequest);
        List<CraftingRequest> craftingRequestUpdated = dataSource.saveCraftingRequests(craftingRequests);
        return craftingRequestUpdated;
    }

    private List<Ring> createRings(UseCaseValidator validator, CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest) {
        final Collection<Long> diamondSpecIds = new HashSet<>();
        diamondSpecIds.add(firstCraftingRequest.getDiamondSpecification().getId());
        diamondSpecIds.add(secondCraftingRequest.getDiamondSpecification().getId());

        final Collection<Diamond> unusedDiamonds;
        if (diamondSpecIds.size() == 1) {
            unusedDiamonds = dataSource.getUnusedDiamondsFromSpecsAndBranch(diamondSpecIds, firstCraftingRequest.getBranch().getId());
        } else {
            unusedDiamonds = new HashSet<>();
            dataSource.getUnusedDiamondFromSpecAndBranch(firstCraftingRequest.getDiamondSpecification().getId(), firstCraftingRequest.getBranch().getId())
                    .ifPresent(unusedDiamonds::add);
            dataSource.getUnusedDiamondFromSpecAndBranch(secondCraftingRequest.getDiamondSpecification().getId(), secondCraftingRequest.getBranch().getId())
                    .ifPresent(unusedDiamonds::add);
        }
        validator.validateAndStopExecution(unusedDiamonds.size() == 2, AcceptCraftingRequestErrorCode.NOT_ENOUGH_UNUSED_DIAMONDS);

        Ring firstRing = Ring.builder()
                .branch(firstCraftingRequest.getBranch())
                .status(RingStatus.NOT_AVAIL)
                .spouse(firstCraftingRequest.getCustomDesign().getSpouse())
                .customDesign(firstCraftingRequest.getCustomDesign())
                .fingerSize(firstCraftingRequest.getFingerSize())
                .engraving(firstCraftingRequest.getEngraving())
                .build();
        firstRing = dataSource.save(firstRing);

        final RingDiamond firstRingDiamond = RingDiamond.builder()
                .ring(firstRing)
                .diamond(unusedDiamonds.stream()
                        .min(Comparator.comparing(Diamond::getId))
                        .orElseThrow(() -> new IllegalStateException("Cannot get first unused Diamond")))
                .build();
        dataSource.save(firstRingDiamond);

        Ring secondRing = Ring.builder()
                .branch(secondCraftingRequest.getBranch())
                .status(RingStatus.NOT_AVAIL)
                .spouse(secondCraftingRequest.getCustomDesign().getSpouse())
                .customDesign(secondCraftingRequest.getCustomDesign())
                .fingerSize(secondCraftingRequest.getFingerSize())
                .engraving(secondCraftingRequest.getEngraving())
                .build();
        secondRing = dataSource.save(secondRing);

        final RingDiamond secondRingDiamond = RingDiamond.builder()
                .ring(secondRing)
                .diamond(unusedDiamonds.stream()
                        .max(Comparator.comparing(Diamond::getId))
                        .orElseThrow(() -> new IllegalStateException("Cannot get second unused Diamond")))
                .build();
        dataSource.save(secondRingDiamond);

        List<Ring> rings = new ArrayList<>();
        rings.add(firstRing);
        rings.add(secondRing);
        return rings;
    }

    private CustomOrder createCustomOrder(CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest, List<Ring> ringsCreated, Contract contractCreated) {
        Configuration configuration = dataSource.getConfigurationForSideDiamond();
        double sideDiamondPrice = Double.parseDouble(configuration.getValue());
        Money firstRingPrice = calculationTotalPriceService.calculationTotalPrice(firstCraftingRequest.getMetalSpecification().getPricePerUnit(), firstCraftingRequest.getDiamondSpecification().getPrice(), firstCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue(), firstCraftingRequest.getCustomDesign().getSideDiamondsCount(), sideDiamondPrice);
        Money secondRingPrice = calculationTotalPriceService.calculationTotalPrice(secondCraftingRequest.getMetalSpecification().getPricePerUnit(), secondCraftingRequest.getDiamondSpecification().getPrice(), secondCraftingRequest.getCustomDesign().getMetalWeight().getWeightValue(), secondCraftingRequest.getCustomDesign().getSideDiamondsCount(), sideDiamondPrice);
        Money totalPrice = Money.create(firstRingPrice.getAmount().add(secondRingPrice.getAmount()));
        CustomOrder customOrder = CustomOrder.builder()
                .customer(firstCraftingRequest.getCustomer())
                .firstRing(ringsCreated.stream()
                        .filter(r -> Objects.equals(r.getCustomDesign().getId(), firstCraftingRequest.getCustomDesign().getId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Cannot find ring of first crafting request")))
                .secondRing(ringsCreated.stream()
                        .filter(r -> Objects.equals(r.getCustomDesign().getId(), secondCraftingRequest.getCustomDesign().getId()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Cannot find ring of second crafting request")))
                .contract(contractCreated)
                .status(CustomOrderStatus.PENDING)
                .totalPrice(totalPrice)
                .build();
        CustomOrder customOrderCreated = dataSource.saveCustomOrder(customOrder);
        return customOrderCreated;
    }

    private Contract createContract() {
        Contract contract = Contract.builder()
                .build();
        Contract contractCreated = dataSource.saveContract(contract);
        return contractCreated;
    }

    private void disableCustomDesigns(CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest) {
        CustomDesign firstCustomDesign = firstCraftingRequest.getCustomDesign();
        firstCustomDesign.setState(State.INACTIVE);
        dataSource.save(firstCustomDesign);
        CustomDesign secondCustomDesign = secondCraftingRequest.getCustomDesign();
        secondCustomDesign.setState(State.INACTIVE);
        dataSource.save(secondCustomDesign);
    }

    private void completeCustomRequest(CraftingRequest firstCraftingRequest) {
        DesignCustomRequest designCustomRequest = firstCraftingRequest.getCustomDesign().getDesignVersion().getDesign().getDesignCustomRequests().stream()
                .filter(x -> x.getCustomRequest().getStatus() == CustomRequestStatus.APPROVED).findFirst().get();
        CustomRequest customRequest = designCustomRequest.getCustomRequest();
        customRequest.setStatus(CustomRequestStatus.COMPLETED);
        dataSource.save(customRequest);
    }

    private void createCraftingStages(CustomOrder customOrderCreated) {
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
        dataSource.saveStages(craftingStages);
    }
}
