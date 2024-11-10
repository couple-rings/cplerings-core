package com.cplerings.core.application.crafting.implementation;

import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_ALREADY_COMPLETED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_ID_REQUIRED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_IS_NOT_PAID;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.CRAFTING_STAGE_NOT_FOUND;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.IMAGE_ID_REQUIRED;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.IMAGE_NOT_FOUND;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.INVALID_CRAFTING_STAGE_ID;
import static com.cplerings.core.application.crafting.error.CompleteCraftingStageErrorCode.INVALID_IMAGE_ID;

import com.cplerings.core.application.crafting.CompleteCraftingStageUseCase;
import com.cplerings.core.application.crafting.datasource.CompleteCraftingStageDataSource;
import com.cplerings.core.application.crafting.input.CompleteCraftingStageInput;
import com.cplerings.core.application.crafting.output.CompleteCraftingStageOutput;
import com.cplerings.core.application.shared.mapper.ACraftingMapper;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.Objects;

@UseCaseImplementation
@RequiredArgsConstructor
public class CompleteCraftingStageUseCaseImpl extends AbstractUseCase<CompleteCraftingStageInput, CompleteCraftingStageOutput>
        implements CompleteCraftingStageUseCase {

    private final CompleteCraftingStageDataSource dataSource;
    private final ACraftingMapper craftingMapper;

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
        final Image image = dataSource.findImageById(input.imageId())
                .orElse(null);
        validator.validateAndStopExecution(image != null, IMAGE_NOT_FOUND);
        craftingStage.setImage(image);
        craftingStage.setCompletionDate(TemporalUtils.getCurrentInstantUTC());
        if (craftingStageIsFinalOne(craftingStage)) {
            final CustomOrder customOrder = craftingStage.getCustomOrder();
            customOrder.setStatus(CustomOrderStatus.DONE);
            dataSource.save(customOrder);
        }
        craftingStage = dataSource.save(craftingStage);
        return CompleteCraftingStageOutput.builder()
                .craftingStage(craftingMapper.toCraftingStage(craftingStage))
                .build();
    }

    private boolean craftingStageIsFinalOne(CraftingStage craftingStage) {
        final CraftingStage finalCraftingStage = craftingStage.getCustomOrder().getCraftingStages()
                .stream()
                .max(Comparator.comparing(CraftingStage::getProgress))
                .orElseThrow(() -> new IllegalStateException("No crafting stages in the custom order"));
        return Objects.equals(finalCraftingStage.getId(), craftingStage.getId());
    }
}
