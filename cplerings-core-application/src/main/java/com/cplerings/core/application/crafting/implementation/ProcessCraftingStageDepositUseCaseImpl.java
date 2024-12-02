package com.cplerings.core.application.crafting.implementation;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.cplerings.core.application.crafting.ProcessCraftingStageDepositUseCase;
import com.cplerings.core.application.crafting.datasource.ProcessCraftingStageDepositDataSource;
import com.cplerings.core.application.crafting.error.ProcessCraftingStageDepositErrorCode;
import com.cplerings.core.application.payment.error.PaymentErrorCode;
import com.cplerings.core.application.payment.input.PaymentSuccessfulResultInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.crafting.CraftingStage;
import com.cplerings.core.domain.crafting.CraftingStageHistory;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.spouse.Agreement;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ProcessCraftingStageDepositUseCaseImpl extends AbstractUseCase<PaymentSuccessfulResultInput, NoOutput> implements ProcessCraftingStageDepositUseCase {

    private final ProcessCraftingStageDepositDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, PaymentSuccessfulResultInput input) {
        super.validateInput(validator, input);
        validator.validate(input.payment() != null, PaymentErrorCode.PAYMENT_REQUIRED);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, PaymentSuccessfulResultInput input) {
        CraftingStage craftingStage = input.payment().getCraftingStage();
        validator.validateAndStopExecution(craftingStage != null, ProcessCraftingStageDepositErrorCode.CRAFTING_STAGE_NOT_FOUND);
        validator.validateAndStopExecution(craftingStage.getStatus() == CraftingStageStatus.PENDING, ProcessCraftingStageDepositErrorCode.CRAFTING_STAGE_NOT_PENDING);
        craftingStage.setStatus(CraftingStageStatus.PAID);
        craftingStage = dataSource.save(craftingStage);
        CraftingStageHistory craftingStageHistory = CraftingStageHistory.builder()
                .craftingStage(craftingStage)
                .status(CraftingStageStatus.PAID)
                .build();
        dataSource.save(craftingStageHistory);
        final Set<CraftingStage> craftingStages = new HashSet<>(craftingStage.getCustomOrder().getCraftingStages());
        final CraftingStage firstCraftingStage = craftingStages.stream()
                .min(Comparator.comparing(CraftingStage::getProgress))
                .orElse(null);
        validator.validateAndStopExecution(firstCraftingStage != null, ProcessCraftingStageDepositErrorCode.NO_CRAFTING_STAGES_IN_CUSTOM_ORDER);
        if (Objects.equals(firstCraftingStage.getId(), craftingStage.getId())) {
            final CustomOrder customOrder = craftingStage.getCustomOrder();
            customOrder.setStatus(CustomOrderStatus.WAITING);
            CustomOrder customOrderUpdated = dataSource.save(customOrder);
            CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                    .status(CustomOrderStatus.WAITING)
                    .customOrder(customOrderUpdated)
                    .build();
            dataSource.save(customOrderHistory);
            return NoOutput.INSTANCE;
        }
        final CraftingStage finalCraftingStage = craftingStages.stream()
                .max(Comparator.comparing(CraftingStage::getProgress))
                .orElse(null);
        validator.validateAndStopExecution(finalCraftingStage != null, ProcessCraftingStageDepositErrorCode.NO_CRAFTING_STAGES_IN_CUSTOM_ORDER);
        if (Objects.equals(finalCraftingStage.getId(), craftingStage.getId())) {
            final CustomOrder customOrder = craftingStage.getCustomOrder();
            final TransportationAddress transportationAddress = customOrder.getTransportationAddress();
            if (transportationAddress != null) {
                final TransportationOrder transportationOrder = TransportationOrder.builder()
                        .customOrder(customOrder)
                        .deliveryAddress(transportationAddress.getAddressAsString())
                        .receiverName(transportationAddress.getReceiverName())
                        .status(TransportStatus.PENDING)
                        .receiverPhone(transportationAddress.getReceiverPhone())
                        .build();
                TransportationOrder transportationOrderCreated = dataSource.save(transportationOrder);

                TransportOrderHistory transportOrderHistory = TransportOrderHistory.builder()
                        .transportationOrder(transportationOrderCreated)
                        .status(TransportStatus.PENDING)
                        .build();
                dataSource.save(transportOrderHistory);
            }
            final Agreement agreement = Agreement.builder()
                    .customer(customOrder.getCustomer())
                    .build();
            dataSource.save(agreement);
        }
        return NoOutput.INSTANCE;
    }
}
