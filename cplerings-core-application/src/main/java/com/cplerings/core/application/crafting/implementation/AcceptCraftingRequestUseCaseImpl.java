package com.cplerings.core.application.crafting.implementation;

import com.cplerings.core.application.crafting.AcceptCraftingRequestUseCase;
import com.cplerings.core.application.crafting.datasource.AcceptCraftingRequestDataSource;
import com.cplerings.core.application.crafting.error.AcceptCraftingRequestErrorCode;
import com.cplerings.core.application.crafting.input.AcceptCraftingRequestInput;
import com.cplerings.core.application.crafting.mapper.AAcceptCraftingRequestMapper;
import com.cplerings.core.application.crafting.output.AcceptCraftingRequestOutput;
import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;
import com.cplerings.core.application.shared.mapper.AEnumMapper;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.price.CalculationService;
import com.cplerings.core.application.shared.service.price.CraftingStageAmounts;
import com.cplerings.core.application.shared.service.price.CraftingStageInfo;
import com.cplerings.core.application.shared.service.price.CustomOrderInfo;
import com.cplerings.core.application.shared.service.price.RingInfo;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.domain.contract.Contract;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageHistory;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.crafting.CraftingRequest;
import com.cplerings.core.domain.design.crafting.CraftingRequestHistory;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.request.CustomRequestHistory;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.design.request.DesignCustomRequest;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.ring.RingHistory;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@UseCaseImplementation
public class AcceptCraftingRequestUseCaseImpl extends AbstractUseCase<AcceptCraftingRequestInput, AcceptCraftingRequestOutput> implements AcceptCraftingRequestUseCase {

    @Getter
    @Builder
    private static final class Configs {

        private Money sideDiamondPrice;
    }

    private static final String FIRST_CRAFTING_STAGE_NAME = "acceptCraftingRequest.firstCraftingStageName";
    private static final String SECOND_CRAFTING_STAGE_NAME = "acceptCraftingRequest.secondCraftingStageName";
    private static final String THIRD_CRAFTING_STAGE_NAME = "acceptCraftingRequest.thirdCraftingStageName";

    private final AAcceptCraftingRequestMapper mapper;
    private final AcceptCraftingRequestDataSource dataSource;
    private final CalculationService calculationService;
    private final ConfigurationService configurationService;
    private final AEnumMapper enumMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, AcceptCraftingRequestInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.getFirstCraftingRequestId() > 0, AcceptCraftingRequestErrorCode.CRAFTING_REQUEST_ID_WRONG_POSITIVE_INTEGER);
        validator.validateAndStopExecution(input.getSecondCraftingRequestId() > 0, AcceptCraftingRequestErrorCode.CRAFTING_REQUEST_ID_WRONG_POSITIVE_INTEGER);
        validator.validateAndStopExecution(input.getStatus() != null, AcceptCraftingRequestErrorCode.STATUS_REQUIRED);

        if (input.getStatus() == ACraftingRequestStatus.REJECTED) {
            validator.validateAndStopExecution(input.getFirstCommentCrafting() != null, AcceptCraftingRequestErrorCode.COMMENT_REQUIRED);
            validator.validateAndStopExecution(input.getSecondCommentCrafting() != null, AcceptCraftingRequestErrorCode.COMMENT_REQUIRED);
        } else {
            validator.validateAndStopExecution(input.getFirstCraftingRequestDifficulty() != null, AcceptCraftingRequestErrorCode.DIFFICULTY_REQUIRED);
            validator.validateAndStopExecution(input.getSecondCraftingRequestDifficulty() != null, AcceptCraftingRequestErrorCode.DIFFICULTY_REQUIRED);
        }
    }

    @Override
    protected AcceptCraftingRequestOutput internalExecute(UseCaseValidator validator, AcceptCraftingRequestInput input) {
        CraftingRequest firstCraftingRequest = dataSource.getCraftingRequestById(input.getFirstCraftingRequestId())
                .orElse(null);
        validator.validateAndStopExecution(firstCraftingRequest != null, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_ID);
        validator.validateAndStopExecution(firstCraftingRequest.getCraftingRequestStatus() == CraftingRequestStatus.PENDING, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_STATUS);
        CraftingRequest secondCraftingRequest = dataSource.getCraftingRequestById(input.getSecondCraftingRequestId())
                .orElse(null);
        validator.validateAndStopExecution(secondCraftingRequest != null, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_ID);
        validator.validateAndStopExecution(secondCraftingRequest.getCraftingRequestStatus() == CraftingRequestStatus.PENDING, AcceptCraftingRequestErrorCode.INVALID_CRAFTING_REQUEST_STATUS);

        if (input.getStatus() == ACraftingRequestStatus.ACCEPTED) {
            List<CraftingRequest> craftingRequests = acceptCraftingRequests(input, firstCraftingRequest, secondCraftingRequest);

            final Configs configs = Configs.builder()
                    .sideDiamondPrice(configurationService.getSideDiamondPrice())
                    .build();

            List<Ring> rings = createRings(validator, firstCraftingRequest, secondCraftingRequest, input, configs);

            Contract contract = createContract();

            CustomOrder customOrder = createCustomOrder(firstCraftingRequest, secondCraftingRequest, rings, contract);

            createCraftingStages(customOrder);

            completeCustomRequest(firstCraftingRequest);

            disableCustomDesigns(firstCraftingRequest, secondCraftingRequest);

            return mapper.toOutput(customOrder, craftingRequests.get(0), craftingRequests.get(1));
        }

        if (input.getStatus() == ACraftingRequestStatus.REJECTED) {
            firstCraftingRequest.setComment(input.getFirstCommentCrafting());
            secondCraftingRequest.setComment(input.getSecondCommentCrafting());

            firstCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.REJECTED);
            secondCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.REJECTED);

            List<CraftingRequest> craftingRequests = Arrays.asList(firstCraftingRequest, secondCraftingRequest);
            craftingRequests = dataSource.saveCraftingRequests(craftingRequests);

            craftingRequests.forEach(x -> {
                CraftingRequestHistory craftingRequestHistory = CraftingRequestHistory.builder()
                        .craftingRequest(x)
                        .status(CraftingRequestStatus.REJECTED)
                        .build();
                dataSource.save(craftingRequestHistory);
            });

            return mapper.toOutput(null, craftingRequests.get(0), craftingRequests.get(1));
        }

        validator.validateAndStopExecution(false, AcceptCraftingRequestErrorCode.WRONG_STATUS);
        return null;
    }

    private List<CraftingRequest> acceptCraftingRequests(AcceptCraftingRequestInput input, CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest) {
        firstCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.ACCEPTED);
        secondCraftingRequest.setCraftingRequestStatus(CraftingRequestStatus.ACCEPTED);

        firstCraftingRequest.setDifficulty(enumMapper.toDifficulty(input.getFirstCraftingRequestDifficulty()));
        secondCraftingRequest.setDifficulty(enumMapper.toDifficulty(input.getSecondCraftingRequestDifficulty()));

        if (input.getFirstCommentCrafting() != null) {
            firstCraftingRequest.setComment(input.getFirstCommentCrafting());
        }

        if (input.getSecondCommentCrafting() != null) {
            secondCraftingRequest.setComment(input.getSecondCommentCrafting());
        }

        List<CraftingRequest> craftingRequests = Arrays.asList(firstCraftingRequest, secondCraftingRequest);
        craftingRequests = dataSource.saveCraftingRequests(craftingRequests);

        craftingRequests.forEach(x -> {
            CraftingRequestHistory craftingRequestHistory = CraftingRequestHistory.builder()
                    .craftingRequest(x)
                    .status(CraftingRequestStatus.ACCEPTED)
                    .build();
            dataSource.save(craftingRequestHistory);
        });

        return craftingRequests;
    }

    private List<Ring> createRings(UseCaseValidator validator, CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest, AcceptCraftingRequestInput input, Configs configs) {
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

        final RingInfo firstRingInfo = RingInfo.builder()
                .metalPricePerUnit(firstCraftingRequest.getMetalSpecification().getPricePerUnit())
                .metalWeight(firstCraftingRequest.getCustomDesign().getMetalWeight())
                .diamondPrice(firstCraftingRequest.getDiamondSpecification().getPrice())
                .sideDiamondPrice(configs.getSideDiamondPrice())
                .sideDiamondCount(firstCraftingRequest.getCustomDesign().getSideDiamondsCount())
                .craftingFee(calculationService.calculateCraftingFee(enumMapper.toDifficulty(input.getFirstCraftingRequestDifficulty())))
                .build();

        Ring firstRing = Ring.builder()
                .branch(firstCraftingRequest.getBranch())
                .status(RingStatus.NOT_AVAIL)
                .spouse(firstCraftingRequest.getCustomDesign().getSpouse())
                .customDesign(firstCraftingRequest.getCustomDesign())
                .fingerSize(firstCraftingRequest.getFingerSize())
                .engraving(firstCraftingRequest.getEngraving())
                .metalSpecification(firstCraftingRequest.getMetalSpecification())
                .difficulty(enumMapper.toDifficulty(input.getFirstCraftingRequestDifficulty()))
                .diamondPrice(firstRingInfo.getDiamondPrice())
                .metalPricePerUnit(firstRingInfo.getMetalPricePerUnit())
                .sideDiamondPrice(firstRingInfo.getSideDiamondPrice())
                .craftingFee(firstRingInfo.getCraftingFee())
                .price(calculationService.calculateRingPrice(firstRingInfo))
                .build();
        firstRing = dataSource.save(firstRing);

        RingHistory firstRingHistory = RingHistory.builder()
                .ring(firstRing)
                .status(RingStatus.NOT_AVAIL)
                .build();
        dataSource.save(firstRingHistory);

        final RingDiamond firstRingDiamond = RingDiamond.builder()
                .ring(firstRing)
                .diamond(unusedDiamonds.stream()
                        .min(Comparator.comparing(Diamond::getId))
                        .orElseThrow(() -> new IllegalStateException("Cannot get first unused Diamond")))
                .build();
        dataSource.save(firstRingDiamond);

        final RingInfo secondRingInfo = RingInfo.builder()
                .metalPricePerUnit(secondCraftingRequest.getMetalSpecification().getPricePerUnit())
                .metalWeight(secondCraftingRequest.getCustomDesign().getMetalWeight())
                .diamondPrice(secondCraftingRequest.getDiamondSpecification().getPrice())
                .sideDiamondPrice(configs.getSideDiamondPrice())
                .sideDiamondCount(secondCraftingRequest.getCustomDesign().getSideDiamondsCount())
                .craftingFee(calculationService.calculateCraftingFee(enumMapper.toDifficulty(input.getSecondCraftingRequestDifficulty())))
                .build();

        Ring secondRing = Ring.builder()
                .branch(secondCraftingRequest.getBranch())
                .status(RingStatus.NOT_AVAIL)
                .spouse(secondCraftingRequest.getCustomDesign().getSpouse())
                .customDesign(secondCraftingRequest.getCustomDesign())
                .fingerSize(secondCraftingRequest.getFingerSize())
                .engraving(secondCraftingRequest.getEngraving())
                .metalSpecification(secondCraftingRequest.getMetalSpecification())
                .difficulty(enumMapper.toDifficulty(input.getSecondCraftingRequestDifficulty()))
                .diamondPrice(secondRingInfo.getDiamondPrice())
                .metalPricePerUnit(secondRingInfo.getMetalPricePerUnit())
                .sideDiamondPrice(secondRingInfo.getSideDiamondPrice())
                .craftingFee(secondRingInfo.getCraftingFee())
                .price(calculationService.calculateRingPrice(secondRingInfo))
                .build();
        secondRing = dataSource.save(secondRing);

        RingHistory secondRingHistory = RingHistory.builder()
                .ring(firstRing)
                .status(RingStatus.NOT_AVAIL)
                .build();
        dataSource.save(secondRingHistory);

        final RingDiamond secondRingDiamond = RingDiamond.builder()
                .ring(secondRing)
                .diamond(unusedDiamonds.stream()
                        .max(Comparator.comparing(Diamond::getId))
                        .orElseThrow(() -> new IllegalStateException("Cannot get second unused Diamond")))
                .build();
        dataSource.save(secondRingDiamond);

        unusedDiamonds.forEach(diamond -> {
            diamond.setState(State.INACTIVE);
            dataSource.save(diamond);
        });

        return Arrays.asList(firstRing, secondRing);
    }

    private CustomOrder createCustomOrder(CraftingRequest firstCraftingRequest, CraftingRequest secondCraftingRequest, List<Ring> ringsCreated, Contract contractCreated) {
        final CustomOrderInfo customOrderInfo = CustomOrderInfo.builder()
                .ringPrices(ringsCreated.stream()
                        .map(Ring::getPrice)
                        .collect(Collectors.toList()))
                .build();
        final Money totalPrice = calculationService.calculateTotalPrice(customOrderInfo);

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
                .shippingFee(configurationService.getShippingFee())
                .totalPrice(totalPrice)
                .build();
        CustomOrder customOrderCreated = dataSource.saveCustomOrder(customOrder);

        CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                .customOrder(customOrderCreated)
                .status(CustomOrderStatus.PENDING)
                .build();
        dataSource.save(customOrderHistory);

        return customOrderCreated;
    }

    private Contract createContract() {
        Contract contract = Contract.builder().build();
        return dataSource.saveContract(contract);
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
        if (firstCraftingRequest.getCustomDesign().getDesignVersion().getVersionNumber() != 0) {
            DesignCustomRequest designCustomRequest = firstCraftingRequest.getCustomDesign().getDesignVersion().getDesign().getDesignCustomRequests().stream()
                    .filter(x -> x.getCustomRequest().getStatus() == CustomRequestStatus.APPROVED)
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
            CustomRequest customRequest = designCustomRequest.getCustomRequest();
            customRequest.setStatus(CustomRequestStatus.COMPLETED);
            CustomRequest customRequestUpdated = dataSource.save(customRequest);
            CustomRequestHistory customRequestHistory = CustomRequestHistory.builder()
                    .status(CustomRequestStatus.COMPLETED)
                    .customRequest(customRequestUpdated)
                    .build();
            dataSource.save(customRequestHistory);
        }
    }

    private void createCraftingStages(CustomOrder customOrderCreated) {
        final CraftingStageInfo craftingStageInfo = CraftingStageInfo.builder()
                .totalPrice(customOrderCreated.getTotalPrice())
                .build();
        final CraftingStageAmounts craftingStageAmounts = calculationService.calculateCraftingStageAmounts(craftingStageInfo);

        CraftingStage firstStage = CraftingStage.builder()
                .status(CraftingStageStatus.PENDING)
                .customOrder(customOrderCreated)
                .progress(configurationService.getCraftingStageProgress1())
                .amount(craftingStageAmounts.getFirstCraftingStageAmount())
                .name(LocaleUtils.translateLocale(FIRST_CRAFTING_STAGE_NAME))
                .build();

        CraftingStage secondStage = CraftingStage.builder()
                .status(CraftingStageStatus.PENDING)
                .customOrder(customOrderCreated)
                .progress(configurationService.getCraftingStageProgress2())
                .amount(craftingStageAmounts.getSecondCraftingStageAmount())
                .name(LocaleUtils.translateLocale(SECOND_CRAFTING_STAGE_NAME))
                .build();

        CraftingStage thirdStage = CraftingStage.builder()
                .status(CraftingStageStatus.PENDING)
                .customOrder(customOrderCreated)
                .progress(configurationService.getCraftingStageProgress3())
                .amount(craftingStageAmounts.getThirdCraftingStageAmount())
                .name(LocaleUtils.translateLocale(THIRD_CRAFTING_STAGE_NAME))
                .build();

        List<CraftingStage> craftingStages = Arrays.asList(firstStage, secondStage, thirdStage);
        craftingStages = dataSource.saveStages(craftingStages);

        craftingStages.forEach(stage -> {
            CraftingStageHistory craftingStageHistory = CraftingStageHistory.builder()
                    .status(CraftingStageStatus.PENDING)
                    .craftingStage(stage)
                    .build();
            dataSource.save(craftingStageHistory);
        });
    }
}
