package com.cplerings.core.application.order.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cplerings.core.application.order.CreateStandardOrderUseCase;
import com.cplerings.core.application.order.datasource.CreateStandardOrderDataSource;
import com.cplerings.core.application.order.error.CreateStandardOrderErrorCode;
import com.cplerings.core.application.order.input.CreateStandardOrderInput;
import com.cplerings.core.application.order.mapper.ACreateStandardOrderMapper;
import com.cplerings.core.application.order.output.CreateStandardOrderOutput;
import com.cplerings.core.application.shared.service.price.CalculationTotalPriceService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateStandardOrderUseCaseImpl extends AbstractUseCase<CreateStandardOrderInput, CreateStandardOrderOutput> implements CreateStandardOrderUseCase {

    private final CreateStandardOrderDataSource createStandardOrderDataSource;
    private final ACreateStandardOrderMapper aCreateStandardOrderMapper;
    private final CalculationTotalPriceService calculationTotalPriceService;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateStandardOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.jewelryIds() != null, CreateStandardOrderErrorCode.JEWELRIES_ID_REQUIRED);
        validator.validate(input.customerId() != null, CreateStandardOrderErrorCode.CUSTOMER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validateAndStopExecution(input.customerId() > 0, CreateStandardOrderErrorCode.CUSTOMER_ID_WRONG_INTEGER);
    }

    @Override
    protected CreateStandardOrderOutput internalExecute(UseCaseValidator validator, CreateStandardOrderInput input) {
        Account customer = createStandardOrderDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CreateStandardOrderErrorCode.CUSTOMER_NOT_FOUND);
        List<Jewelry> jewelries = new ArrayList<>();
        input.jewelryIds().forEach(x -> {
            Jewelry jewelry = createStandardOrderDataSource.getJewelryById(x)
                    .orElse(null);
            validator.validateAndStopExecution(jewelry != null, CreateStandardOrderErrorCode.JEWELERY_NOT_FOUND);
            jewelries.add(jewelry);
        });
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        jewelries.forEach(x -> {
            var eachJewelryPrice = calculationTotalPriceService.calculationTotalPrice(x.getMetalSpecification().getPricePerUnit(), x.getDiamond().getDiamondSpecification().getPrice(), x.getDesign().getMetalWeight().getWeightValue(), 0, 0);
            BigDecimal decimalPrice = eachJewelryPrice.getAmount();
            totalPrice.add(decimalPrice);
        });
        StandardOrder standardOrder = StandardOrder.builder()
                .status(StandardOrderStatus.PENDING)
                .customer(customer)
                .totalPrice(Money.create(totalPrice))
                .build();
        StandardOrder standardOrderCreated = createStandardOrderDataSource.save(standardOrder);
        StandardOrderHistory standardOrderHistory = StandardOrderHistory.builder()
                .standardOrder(standardOrderCreated)
                .status(StandardOrderStatus.PENDING)
                .build();
        createStandardOrderDataSource.save(standardOrderHistory);
        List<StandardOrderItem> standardOrderItems = new ArrayList<>();
        jewelries.forEach(x -> {
            StandardOrderItem standardOrderItem = StandardOrderItem.builder()
                    .standardOrder(standardOrderCreated)
                    .jewelry(x)
                    .build();
            standardOrderItems.add(standardOrderItem);
        });
        List<StandardOrderItem> items = createStandardOrderDataSource.saveItems(standardOrderItems);
        jewelries.forEach(x -> {
            x.setStatus(JewelryStatus.UNAVAILABLE);
        });
        createStandardOrderDataSource.saveJewelries(jewelries);
        standardOrderCreated.setStandardOrderItems(new HashSet<>(items));
        createStandardOrderDataSource.save(standardOrderCreated);
        if (input.transportationAddressId() != null) {
            TransportationAddress transportationAddress = createStandardOrderDataSource.getAddressById(input.transportationAddressId())
                    .orElse(null);
            validator.validateAndStopExecution(transportationAddress != null, CreateStandardOrderErrorCode.ADDRESS_NOT_FOUND);
            standardOrderCreated.setTransportationAddress(transportationAddress);
            StandardOrder standardOrderUpdated = createStandardOrderDataSource.save(standardOrderCreated);
            TransportationOrder transportationOrder = TransportationOrder.builder()
                    .standardOrder(standardOrderUpdated)
                    .deliveryAddress(transportationAddress.getAddressAsString())
                    .receiverName(transportationAddress.getReceiverName())
                    .receiverPhone(transportationAddress.getReceiverPhone())
                    .status(TransportStatus.PENDING)
                    .build();
            TransportationOrder transportationOrderCreated = createStandardOrderDataSource.save(transportationOrder);
            TransportOrderHistory transportOrderHistory = TransportOrderHistory.builder()
                    .status(TransportStatus.PENDING)
                    .transportationOrder(transportationOrderCreated)
                    .build();
            createStandardOrderDataSource.save(transportOrderHistory);
            return aCreateStandardOrderMapper.toOutput(standardOrderUpdated);
        }

        return aCreateStandardOrderMapper.toOutput(standardOrderCreated);
    }
}
