package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.CUSTOMER_ID_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.CUSTOMER_NOT_FOUND;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.INVALID_CUSTOMER_ID;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.INVALID_PROOF_IMAGE_ID;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.NOTE_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.PAYMENT_METHOD_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.PROOF_IMAGE_ID_REQUIRED;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.PROOF_IMAGE_NOT_FOUND;
import static com.cplerings.core.application.jewelry.error.ResellJewelryErrorCode.WRONG_CUSTOMER;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.cplerings.core.application.order.ResellCustomOrderUseCase;
import com.cplerings.core.application.order.datasource.ResellCustomOrderDataSource;
import com.cplerings.core.application.order.error.ResellCustomOrderErrorCode;
import com.cplerings.core.application.order.input.ResellCustomOrderInput;
import com.cplerings.core.application.order.mapper.ResellCustomOrderMapper;
import com.cplerings.core.application.order.output.ResellCustomOrderOutput;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.resell.PaymentMethod;
import com.cplerings.core.domain.resell.ResellOrder;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Agreement;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class ResellCustomOrderUseCaseImpl extends AbstractUseCase<ResellCustomOrderInput, ResellCustomOrderOutput> implements ResellCustomOrderUseCase {

    private final ResellCustomOrderDataSource dataSource;
    private final ResellCustomOrderMapper mapper;
    private final SecurityService securityService;
    private final ConfigurationService configurationService;

    @Override
    protected void validateInput(UseCaseValidator validator, ResellCustomOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.getCustomOrderId() != null, ResellCustomOrderErrorCode.CUSTOM_ORDER_ID_REQUIRED);
        validator.validate(input.getCustomerId() != null, CUSTOMER_ID_REQUIRED);
        validator.validate(input.getProofImageId() != null, PROOF_IMAGE_ID_REQUIRED);
        validator.validate(input.getPaymentMethod() != null, PAYMENT_METHOD_REQUIRED);
        validator.validate(StringUtils.isNotBlank(input.getNote()), NOTE_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.getCustomOrderId()), ResellCustomOrderErrorCode.INVALID_CUSTOM_ORDER_ID);
        validator.validate(NumberUtils.isPositive(input.getCustomerId()), INVALID_CUSTOMER_ID);
        validator.validate(NumberUtils.isPositive(input.getProofImageId()), INVALID_PROOF_IMAGE_ID);
    }

    @Override
    protected ResellCustomOrderOutput internalExecute(UseCaseValidator validator, ResellCustomOrderInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();

        CustomOrder customOrder = dataSource.getCustomOrderById(input.getCustomOrderId())
                .orElse(null);
        validator.validateAndStopExecution(customOrder != null, ResellCustomOrderErrorCode.CUSTOM_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(customOrder.getStatus() == CustomOrderStatus.COMPLETED, ResellCustomOrderErrorCode.CUSTOM_ORDER_NOT_COMPLETE);

        final Account customer = dataSource.findCustomerById(input.getCustomerId())
                .orElse(null);
        validator.validateAndStopExecution(customer != null, CUSTOMER_NOT_FOUND);

        final Image proofImage = dataSource.findProofImageById(input.getProofImageId())
                .orElse(null);
        validator.validateAndStopExecution(proofImage != null, PROOF_IMAGE_NOT_FOUND);
        validator.validateAndStopExecution(customOrder.getCustomer().getId() == customer.getId(), WRONG_CUSTOMER);

        // update ring status
        Ring firstRing = customOrder.getFirstRing();
        firstRing.setStatus(RingStatus.RESOLD);
        Ring secondRing = customOrder.getSecondRing();
        secondRing.setStatus(RingStatus.RESOLD);
        dataSource.save(firstRing);
        dataSource.save(secondRing);

        final Collection<Ring> rings = Arrays.asList(customOrder.getFirstRing(), customOrder.getSecondRing());
        final Collection<RingDiamond> ringDiamonds = rings.stream()
                .flatMap(ring -> ring.getRingDiamonds().stream())
                .collect(Collectors.toSet());

        final Collection<Diamond> diamonds = ringDiamonds.stream()
                .map(RingDiamond::getDiamond)
                .collect(Collectors.toSet());
        diamonds.forEach(diamond -> diamond.setState(State.ACTIVE));
        dataSource.saveDiamonds(diamonds);

        final Agreement agreement = Optional.ofNullable(customOrder.getCustomer())
                .map(Account::getAgreement)
                .orElseThrow(() -> new IllegalStateException("No agreement was found"));
        dataSource.delete(agreement);

        ResellOrder resellOrder = ResellOrder.builder()
                .customer(customer)
                .staff(dataSource.getStaffReference(currentUser.id()))
                .proofImage(proofImage)
                .note(input.getNote().trim())
                .amount(calculateAmount(customOrder))
                .customOrder(customOrder)
                .build();
        switch (input.getPaymentMethod()) {
            case CASH -> resellOrder.setPaymentMethod(PaymentMethod.CASH);
            case TRANSFER -> resellOrder.setPaymentMethod(PaymentMethod.TRANSFER);
        }
        resellOrder = dataSource.save(resellOrder);

        return mapper.toOutput(resellOrder);
    }

    private Money calculateAmount(CustomOrder customOrder) {
        var resellRatio = configurationService.getResellPercentage();
        Money customOrderPrice = customOrder.getTotalPrice();
        BigDecimal price = customOrderPrice.getAmount().multiply(BigDecimal.valueOf(resellRatio)).divide(BigDecimal.valueOf(100));
        return Money.create(price);
    }
}
