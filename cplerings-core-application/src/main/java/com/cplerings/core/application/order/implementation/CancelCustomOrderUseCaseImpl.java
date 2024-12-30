package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.order.error.CancelCustomOrderErrorCode.CUSTOM_ORDER_ALREADY_CANCELLED;
import static com.cplerings.core.application.order.error.CancelCustomOrderErrorCode.CUSTOM_ORDER_ALREADY_COMPLETED;
import static com.cplerings.core.application.order.error.CancelCustomOrderErrorCode.CUSTOM_ORDER_ID_REQUIRED;
import static com.cplerings.core.application.order.error.CancelCustomOrderErrorCode.CUSTOM_ORDER_NOT_FOUND;
import static com.cplerings.core.application.order.error.CancelCustomOrderErrorCode.INVALID_CUSTOM_ORDER_ID;

import com.cplerings.core.application.order.CancelCustomOrderUseCase;
import com.cplerings.core.application.order.datasource.CancelCustomOrderDataSource;
import com.cplerings.core.application.order.input.CancelCustomOrderInput;
import com.cplerings.core.application.shared.output.NoOutput;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.ring.RingHistory;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.State;

import lombok.RequiredArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UseCaseImplementation
@RequiredArgsConstructor
public class CancelCustomOrderUseCaseImpl extends AbstractUseCase<CancelCustomOrderInput, NoOutput>
        implements CancelCustomOrderUseCase {

    private final CancelCustomOrderDataSource dataSource;

    @Override
    protected void validateInput(UseCaseValidator validator, CancelCustomOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customOrderId() != null, CUSTOM_ORDER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.customOrderId()), INVALID_CUSTOM_ORDER_ID);
    }

    @Override
    protected NoOutput internalExecute(UseCaseValidator validator, CancelCustomOrderInput input) {
        final CustomOrder customOrder = dataSource.findCustomOrderById(input.customOrderId())
                .orElse(null);

        validator.validateAndStopExecution(customOrder != null, CUSTOM_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(customOrder.getStatus() != CustomOrderStatus.CANCELED, CUSTOM_ORDER_ALREADY_CANCELLED);
        validator.validateAndStopExecution(customOrder.getStatus() != CustomOrderStatus.COMPLETED, CUSTOM_ORDER_ALREADY_COMPLETED);

        cancelCustomOrder(customOrder);

        cancelTransportationOrder(customOrder);

        cancelRings(customOrder);

        enableDiamonds(customOrder);

        enableDesigns(customOrder);

        deleteAgreement(customOrder);

        return NoOutput.INSTANCE;
    }

    private void cancelCustomOrder(CustomOrder customOrder) {
        customOrder.setStatus(CustomOrderStatus.CANCELED);
        dataSource.save(customOrder);

        final CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                .customOrder(customOrder)
                .status(CustomOrderStatus.CANCELED)
                .build();
        dataSource.save(customOrderHistory);
    }

    private void cancelTransportationOrder(CustomOrder customOrder) {
        final Collection<TransportationOrder> transportationOrders = CollectionUtils.emptyIfNull(customOrder.getTransportationOrders())
                .stream()
                .filter(transportationOrder -> transportationOrder.getState() == State.ACTIVE)
                .collect(Collectors.toSet());

        transportationOrders.forEach(transportationOrder -> transportationOrder.setState(State.INACTIVE));
        dataSource.saveTransportationOrders(transportationOrders);
    }

    private void cancelRings(CustomOrder customOrder) {
        final Collection<Ring> rings = Arrays.asList(customOrder.getFirstRing(), customOrder.getSecondRing());

        rings.forEach(ring -> {
            ring.setStatus(RingStatus.NOT_AVAIL);

            final RingHistory ringHistory = RingHistory.builder()
                    .ring(ring)
                    .status(RingStatus.NOT_AVAIL)
                    .build();
            dataSource.save(ringHistory);
        });

        dataSource.saveRings(rings);
    }

    private void enableDiamonds(CustomOrder customOrder) {
        final Collection<Diamond> diamonds = Stream.of(customOrder.getFirstRing(), customOrder.getSecondRing())
                .flatMap(ring -> ring.getRingDiamonds().stream())
                .map(RingDiamond::getDiamond)
                .collect(Collectors.toSet());

        diamonds.forEach(diamond -> diamond.setState(State.ACTIVE));

        dataSource.saveDiamonds(diamonds);
    }

    private void enableDesigns(CustomOrder customOrder) {
        final Collection<Design> designs = Stream.of(customOrder.getFirstRing(), customOrder.getSecondRing())
                .map(ring -> ring.getCustomDesign().getDesignVersion().getDesign())
                .collect(Collectors.toSet());

        designs.forEach(design -> design.setStatus(DesignStatus.AVAILABLE));

        dataSource.saveDesigns(designs);
    }

    private void deleteAgreement(CustomOrder customOrder) {
        Optional.ofNullable(customOrder.getCustomer())
                .map(Account::getAgreement)
                .ifPresent(dataSource::delete);
    }
}
