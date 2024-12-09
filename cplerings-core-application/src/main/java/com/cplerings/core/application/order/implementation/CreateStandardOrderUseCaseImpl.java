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
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.price.CalculationTotalPriceService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class CreateStandardOrderUseCaseImpl extends AbstractUseCase<CreateStandardOrderInput, CreateStandardOrderOutput> implements CreateStandardOrderUseCase {

    private final CreateStandardOrderDataSource createStandardOrderDataSource;
    private final ACreateStandardOrderMapper aCreateStandardOrderMapper;
    private final CalculationTotalPriceService calculationTotalPriceService;
    private final ConfigurationService configurationService;

    @Override
    protected void validateInput(UseCaseValidator validator, CreateStandardOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customerId() != null, CreateStandardOrderErrorCode.CUSTOMER_ID_REQUIRED);
        validator.validate(input.metalSpecDesignIds() != null, CreateStandardOrderErrorCode.METAL_SPEC_DESIGN_IDS_REQUIRED);
        validator.validate(input.branchId() != null, CreateStandardOrderErrorCode.BRANCH_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.customerId() > 0, CreateStandardOrderErrorCode.CUSTOMER_ID_WRONG_INTEGER);
        validator.validate(input.branchId() > 0, CreateStandardOrderErrorCode.BRANCH_ID_WRONG_INTEGER);
        validator.clearAndThrowErrorCodes();
    }

    @Override
    protected CreateStandardOrderOutput internalExecute(UseCaseValidator validator, CreateStandardOrderInput input) {
        Account customer = createStandardOrderDataSource.getCustomerById(input.customerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CreateStandardOrderErrorCode.CUSTOMER_NOT_FOUND);
        List<Jewelry> jewelries = new ArrayList<>();
        input.metalSpecDesignIds().forEach(x -> {
            Jewelry jewelry = createStandardOrderDataSource.getJewelry(input.branchId(), x.designId(), x.metalSpecId())
                    .orElse(null);
            validator.validateAndStopExecution(jewelry != null, CreateStandardOrderErrorCode.JEWELRY_NOT_VALID);
            jewelries.add(jewelry);
        });
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        BigDecimal sideDiamondPrice = configurationService.getSideDiamondPrice().getAmount();
        for (var jewelry : jewelries) {
            var eachJewelryPrice = calculationTotalPriceService.calculationPriceForJewelry(
                    jewelry.getMetalSpecification().getPricePerUnit(),
                    jewelry.getDesign().getMetalWeight().getWeightValue(),
                    jewelry.getDesign().getSideDiamondsCount(),
                    sideDiamondPrice
            );
            BigDecimal decimalPrice = eachJewelryPrice.getAmount();
            totalPrice = totalPrice.add(decimalPrice);
        }
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
                    .design(x.getDesign())
                    .branch(x.getBranch())
                    .metalSpecification(x.getMetalSpecification())
                    .build();
            var eachJewelryPrice = calculationTotalPriceService.calculationPriceForJewelry(
                    x.getMetalSpecification().getPricePerUnit(),
                    x.getDesign().getMetalWeight().getWeightValue(),
                    x.getDesign().getSideDiamondsCount(),
                    sideDiamondPrice
            );
            standardOrderItem.setPrice(eachJewelryPrice);
            standardOrderItems.add(standardOrderItem);
        });
        List<StandardOrderItem> items = createStandardOrderDataSource.saveItems(standardOrderItems);
        standardOrderCreated.setStandardOrderItems(new HashSet<>(items));
        createStandardOrderDataSource.save(standardOrderCreated);

        return aCreateStandardOrderMapper.toOutput(standardOrderCreated);
    }
}
