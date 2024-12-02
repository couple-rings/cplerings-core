package com.cplerings.core.application.crafting.implementation;

import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.CRAFTING_STAGE_ID_REQUIRED;
import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.CRAFTING_STAGE_NOT_FOUND;
import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.CRAFTING_STAGE_NOT_IN_PENDING;
import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.CUSTOM_ORDER_STATUS_NOT_VALID;
import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.INVALID_TRANSPORT_ADDRESS_ID;
import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.PREVIOUS_STAGE_NOT_PAID;
import static com.cplerings.core.application.crafting.error.DepositCraftingStageErrorCode.TRANSPORT_ADDRESS_NOT_FOUND;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;

import com.cplerings.core.application.crafting.DepositCraftingStageUseCase;
import com.cplerings.core.application.crafting.datasource.DepositCraftingStageDataSource;
import com.cplerings.core.application.crafting.input.DepositCraftingStageInput;
import com.cplerings.core.application.crafting.output.DepositCraftingStageOutput;
import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class DepositCraftingStageUseCaseImpl extends AbstractUseCase<DepositCraftingStageInput, DepositCraftingStageOutput>
        implements DepositCraftingStageUseCase {

    private final DepositCraftingStageDataSource dataSource;
    private final PaymentRequestService paymentRequestService;

    @Override
    protected void validateInput(UseCaseValidator validator, DepositCraftingStageInput input) {
        super.validateInput(validator, input);
        validator.validateAndStopExecution(input.craftingStageId() != null, CRAFTING_STAGE_ID_REQUIRED);
        validator.validate(input.craftingStageId() > 0, CRAFTING_STAGE_ID_REQUIRED);
    }

    @Override
    protected DepositCraftingStageOutput internalExecute(UseCaseValidator validator, DepositCraftingStageInput input) {
        final CraftingStage craftingStage = dataSource.findCraftingStageById(input.craftingStageId())
                .orElse(null);
        validator.validateAndStopExecution(craftingStage != null, CRAFTING_STAGE_NOT_FOUND);
        validator.validateAndStopExecution(craftingStage.getStatus() == CraftingStageStatus.PENDING, CRAFTING_STAGE_NOT_IN_PENDING);
        validator.validateAndStopExecution(thereIsNoPendingStageBeforeCurrentOne(craftingStage), PREVIOUS_STAGE_NOT_PAID);
        validator.validateAndStopExecution(customOrderStatusIsValidForPayment(craftingStage), CUSTOM_ORDER_STATUS_NOT_VALID);
        if (craftingStageIsTheLastOne(craftingStage) && input.transportationAddressId() != null) {
            validator.validate(NumberUtils.isPositive(input.transportationAddressId()), INVALID_TRANSPORT_ADDRESS_ID);
            final TransportationAddress transportationAddress = dataSource.findTransportAddressById(input.transportationAddressId())
                    .orElse(null);
            validator.validateAndStopExecution(transportationAddress != null, TRANSPORT_ADDRESS_NOT_FOUND);
            final CustomOrder customOrder = craftingStage.getCustomOrder();
            customOrder.setTransportationAddress(transportationAddress);
            dataSource.save(customOrder);
        }
        final PaymentRequest paymentRequest = paymentRequestService.requestPayment(PaymentInfo.builder()
                .amount(calculateAmount(craftingStage))
                .description("Payment for crafting stage " + craftingStage.getId())
                .receiverType(PaymentReceiverType.CRAFT_STAGE)
                .build());
        return DepositCraftingStageOutput.builder()
                .paymentId(paymentRequest.getPayment().getId())
                .paymentLink(paymentRequest.getPaymentLink())
                .build();
    }

    private boolean craftingStageIsTheLastOne(CraftingStage craftingStage) {
        final CraftingStage finalCraftingStage = craftingStage.getCustomOrder().getCraftingStages()
                .stream()
                .max(Comparator.comparing(CraftingStage::getProgress))
                .orElse(null);
        if (finalCraftingStage == null) {
            throw new IllegalStateException("Final crafting stage not found");
        }
        return Objects.equals(finalCraftingStage.getId(), craftingStage.getId());
    }

    private boolean thereIsNoPendingStageBeforeCurrentOne(CraftingStage craftingStage) {
        return craftingStage.getCustomOrder()
                .getCraftingStages()
                .stream()
                .filter(cs -> ObjectUtils.notEqual(cs.getId(), craftingStage.getId())
                        && NumberUtils.isLessThan(cs.getProgress(), craftingStage.getProgress()))
                .noneMatch(cs -> cs.getStatus() == CraftingStageStatus.PENDING);
    }

    private Money calculateAmount(CraftingStage craftingStage) {
        final CraftingStage craftingStageBeforeCurrentOne = craftingStage.getCustomOrder()
                .getCraftingStages()
                .stream()
                .filter(cs -> cs.getProgress() < craftingStage.getProgress())
                .max(Comparator.comparing(CraftingStage::getProgress))
                .orElse(null);
        final BigDecimal percentage = getPercentage(craftingStage, craftingStageBeforeCurrentOne);
        final Money totalPrice = craftingStage.getCustomOrder().getTotalPrice();
        return totalPrice.multiply(percentage);
    }

    private BigDecimal getPercentage(CraftingStage craftingStage, CraftingStage craftingStageBeforeCurrentOne) {
        final BigDecimal percentage;
        if (craftingStageBeforeCurrentOne == null) {
            // First crafting stage
            percentage = BigDecimal.valueOf(craftingStage.getProgress())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
        } else {
            // 2nd crafting stage or later
            percentage = BigDecimal.valueOf(craftingStage.getProgress())
                    .subtract(BigDecimal.valueOf(craftingStageBeforeCurrentOne.getProgress()))
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
        }
        return percentage;
    }

    private boolean customOrderStatusIsValidForPayment(CraftingStage craftingStage) {
        final CustomOrder customOrder = craftingStage.getCustomOrder();
        return customOrder != null
                && (customOrder.getStatus() == CustomOrderStatus.PENDING
                || customOrder.getStatus() == CustomOrderStatus.WAITING
                || customOrder.getStatus() == CustomOrderStatus.IN_PROGRESS);
    }
}
