package com.cplerings.core.application.crafting.implementation;

import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_ALREADY_COMPLETED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_ID_REQUIRED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_IS_NOT_PAID;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_NOT_FOUND;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.IMAGE_ID_REQUIRED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.IMAGE_NOT_FOUND;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.INVALID_CRAFTING_STAGE_ID;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.INVALID_IMAGE_ID;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.INVALID_MAINTENANCES;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.INVALID_RINGS;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.MAINTENANCES_NOT_FOUND;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.PREVIOUS_CRAFTING_STAGE_NOT_COMPLETED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.PREVIOUS_CRAFTING_STAGE_NOT_PAID;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.RINGS_NOT_PART_OF_CUSTOM_ORDER;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.RING_MAINTENANCES_REQUIRED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.TWO_RING_MAINTENANCES_REQUIRED;

import com.cplerings.core.application.crafting.CompleteCraftingStageUseCase;
import com.cplerings.core.application.crafting.datasource.CompleteCraftingStageDataSource;
import com.cplerings.core.application.crafting.input.CompleteCraftingStageInput;
import com.cplerings.core.application.crafting.input.data.RingMaintenance;
import com.cplerings.core.application.crafting.output.CompleteCraftingStageOutput;
import com.cplerings.core.application.shared.mapper.ACraftingMapper;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.file.Document;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.ring.Ring;

import lombok.RequiredArgsConstructor;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@UseCaseImplementation
@RequiredArgsConstructor
public class CompleteCraftingStageUseCaseImpl extends AbstractUseCase<CompleteCraftingStageInput, CompleteCraftingStageOutput>
        implements CompleteCraftingStageUseCase {

    private final CompleteCraftingStageDataSource dataSource;
    private final ACraftingMapper craftingMapper;
    private final ConfigurationService configurationService;

    @Override
    protected void validateInput(UseCaseValidator validator, CompleteCraftingStageInput input) {
        super.validateInput(validator, input);
        validator.validate(input.craftingStageId() != null, CRAFTING_STAGE_ID_REQUIRED);
        validator.validate(NumberUtils.isPositive(input.craftingStageId()), INVALID_CRAFTING_STAGE_ID);
        validator.validate(input.imageId() != null, IMAGE_ID_REQUIRED);
        validator.validate(NumberUtils.isPositive(input.imageId()), INVALID_IMAGE_ID);
    }

    @Override
    protected CompleteCraftingStageOutput internalExecute(UseCaseValidator validator, CompleteCraftingStageInput input) {
        CraftingStage craftingStage = dataSource.findCraftingStageById(input.craftingStageId())
                .orElse(null);
        validator.validateAndStopExecution(craftingStage != null, CRAFTING_STAGE_NOT_FOUND);
        validator.validateAndStopExecution(craftingStage.getCompletionDate() == null, CRAFTING_STAGE_ALREADY_COMPLETED);
        validator.validateAndStopExecution(craftingStage.getStatus() == CraftingStageStatus.PAID, CRAFTING_STAGE_IS_NOT_PAID);
        validator.validateAndStopExecution(previousCraftingStagesArePaid(craftingStage), PREVIOUS_CRAFTING_STAGE_NOT_PAID);
        validator.validateAndStopExecution(previousCraftingStagesAreCompleted(craftingStage), PREVIOUS_CRAFTING_STAGE_NOT_COMPLETED);
        final Image image = dataSource.findImageById(input.imageId())
                .orElse(null);
        validator.validateAndStopExecution(image != null, IMAGE_NOT_FOUND);
        craftingStage.setImage(image);
        craftingStage.setCompletionDate(TemporalUtils.getCurrentInstantUTC());
        if (craftingStageIsFinalOne(craftingStage)) {
            validator.validateAndStopExecution(input.ringMaintenances() != null, RING_MAINTENANCES_REQUIRED);
            validator.validateAndStopExecution(input.ringMaintenances().size() == 2, TWO_RING_MAINTENANCES_REQUIRED);
            final Set<Long> ringIds = input.ringMaintenances()
                    .stream()
                    .map(RingMaintenance::ringId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            validator.validateAndStopExecution(ringIds.size() == 2, INVALID_RINGS);
            final Set<Long> maintenanceIds = input.ringMaintenances()
                    .stream()
                    .map(RingMaintenance::maintenanceDocumentId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            validator.validateAndStopExecution(maintenanceIds.size() == 2, INVALID_MAINTENANCES);

            validator.validateAndStopExecution(ringIds.contains(craftingStage.getCustomOrder().getFirstRing().getId())
                    && ringIds.contains(craftingStage.getCustomOrder().getSecondRing().getId()), RINGS_NOT_PART_OF_CUSTOM_ORDER);

            final Set<Document> maintenances = dataSource.getMaintenancesByIds(maintenanceIds);
            validator.validateAndStopExecution(maintenances.size() == 2, MAINTENANCES_NOT_FOUND);

            final Ring firstRing = craftingStage.getCustomOrder().getFirstRing();
            updateRingMaintenance(input, maintenances, firstRing);

            final Ring secondRing = craftingStage.getCustomOrder().getSecondRing();
            updateRingMaintenance(input, maintenances, secondRing);

            final CustomOrder customOrder = craftingStage.getCustomOrder();
            customOrder.setStatus(CustomOrderStatus.DONE);
            dataSource.save(customOrder);
        }
        craftingStage = dataSource.save(craftingStage);
        return CompleteCraftingStageOutput.builder()
                .craftingStage(craftingMapper.toCraftingStage(craftingStage))
                .build();
    }

    private void updateRingMaintenance(CompleteCraftingStageInput input, Set<Document> maintenances, Ring ring) {
        final RingMaintenance maintenance = input.ringMaintenances()
                .stream()
                .filter(rm -> Objects.equals(rm.ringId(), ring.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Ring not found"));
        final Document secondMaintenance = maintenances.stream()
                .filter(m -> Objects.equals(m.getId(), maintenance.maintenanceDocumentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Document not found"));
        ring.setMaintenanceDocument(secondMaintenance);
        ring.setMaintenanceExpiredDate(TemporalUtils.getCurrentInstantUTCExcludeTimePartAndBelow()
                .plus(configurationService.getMaximumMaintenanceDuration() * 365L, ChronoUnit.DAYS));
        dataSource.save(ring);
    }

    private boolean previousCraftingStagesAreCompleted(CraftingStage craftingStage) {
        return craftingStage.getCustomOrder().getCraftingStages()
                .stream()
                .filter(cs -> NumberUtils.isLessThan(cs.getId(), craftingStage.getId()))
                .allMatch(cs -> cs.getCompletionDate() != null);
    }

    private boolean previousCraftingStagesArePaid(CraftingStage craftingStage) {
        return craftingStage.getCustomOrder().getCraftingStages()
                .stream()
                .filter(cs -> NumberUtils.isLessThan(cs.getId(), craftingStage.getId()))
                .allMatch(cs -> cs.getStatus() == CraftingStageStatus.PAID);
    }

    private boolean craftingStageIsFinalOne(CraftingStage craftingStage) {
        final CraftingStage finalCraftingStage = craftingStage.getCustomOrder().getCraftingStages()
                .stream()
                .max(Comparator.comparing(CraftingStage::getProgress))
                .orElseThrow(() -> new IllegalStateException("No crafting stages in the custom order"));
        return Objects.equals(finalCraftingStage.getId(), craftingStage.getId());
    }
}
