package com.cplerings.core.application.order.implementation;

import com.cplerings.core.application.order.RefundStandardOrderUseCase;
import com.cplerings.core.application.order.datasource.RefundStandardOrderDataSource;
import com.cplerings.core.application.order.error.RefundStandardOrderErrorCode;
import com.cplerings.core.application.order.input.RefundStandardOrderInput;
import com.cplerings.core.application.order.mapper.ARefundStandardOrderMapper;
import com.cplerings.core.application.order.output.RefundStandardOrderOutput;
import com.cplerings.core.application.shared.mapper.AEnumMapper;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.locale.LocaleUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.payment.Payment;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@RequiredArgsConstructor
@UseCaseImplementation
public class RefundStandardOrderUseCaseImpl extends AbstractUseCase<RefundStandardOrderInput, RefundStandardOrderOutput> implements RefundStandardOrderUseCase {

    private static final String PAYMENT_DESCRIPTION_LOCALE = "refundStandardOrder.paymentDescription";

    private final RefundStandardOrderDataSource dataSource;
    private final ARefundStandardOrderMapper aRefundStandardOrderMapper;
    private final ConfigurationService configurationService;
    private final AEnumMapper aEnumMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, RefundStandardOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.refundStandardOrderRequestData() != null, RefundStandardOrderErrorCode.REFUND_DATA_REQUIRED);
        validator.validate(input.standardOrderId() != null, RefundStandardOrderErrorCode.STANDARD_ORDER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.refundStandardOrderRequestData().reason() != null, RefundStandardOrderErrorCode.REASON_REQUIRED);
        validator.validate(input.refundStandardOrderRequestData().staffId() != null, RefundStandardOrderErrorCode.STAFF_ID_REQUIRED);
        validator.validate(input.refundStandardOrderRequestData().proofImageId() != null, RefundStandardOrderErrorCode.IMAGE_ID_REQUIRED);
        validator.validate(input.refundStandardOrderRequestData().refundMethod() != null, RefundStandardOrderErrorCode.REFUND_METHOD_REQUIRED);
        validator.clearAndThrowErrorCodes();
        validator.validate(input.standardOrderId() > 0, RefundStandardOrderErrorCode.STANDARD_ORDER_ID_WRONG_INTEGER);
        validator.validate(input.refundStandardOrderRequestData().staffId() > 0, RefundStandardOrderErrorCode.STAFF_ID_WRONG_INTEGER);
        validator.validate(input.refundStandardOrderRequestData().proofImageId() > 0, RefundStandardOrderErrorCode.IMAGE_ID_WRONG_INTEGER);
        validator.clearAndThrowErrorCodes();
    }

    @Override
    protected RefundStandardOrderOutput internalExecute(UseCaseValidator validator, RefundStandardOrderInput input) {
        StandardOrder standardOrder = dataSource.getStandardOrderWithOrderItem(input.standardOrderId())
                .orElse(null);
        validator.validateAndStopExecution(standardOrder != null, RefundStandardOrderErrorCode.STANDARD_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(standardOrder.getStatus() == StandardOrderStatus.PAID
                || standardOrder.getStatus() == StandardOrderStatus.COMPLETED, RefundStandardOrderErrorCode.WRONG_STATUS_FOR_REFUNDED);

        Account staff = dataSource.getStaffById(input.refundStandardOrderRequestData().staffId())
                .orElse(null);
        validator.validateAndStopExecution(staff != null, RefundStandardOrderErrorCode.STAFF_NOT_FOUND);

        Image proofImage = dataSource.getImageById(input.refundStandardOrderRequestData().proofImageId())
                .orElse(null);
        validator.validateAndStopExecution(proofImage != null, RefundStandardOrderErrorCode.IMAGE_NOT_FOUND);

        standardOrder.getStandardOrderItems().forEach(standardOrderItem -> {
            Jewelry jewelry = standardOrderItem.getJewelry();
            jewelry.setStatus(JewelryStatus.AVAILABLE);
            dataSource.save(jewelry);
        });

        BigDecimal refundPercentage = BigDecimal.valueOf(configurationService.getRefundPercentage());
        Money amount = standardOrder.getTotalPrice()
                .multiply(refundPercentage)
                .divide(BigDecimal.valueOf(100));

        Payment payment = Payment.builder()
                .type(aEnumMapper.toPaymentType(input.refundStandardOrderRequestData().refundMethod()))
                .description(String.format(LocaleUtils.translateLocale(PAYMENT_DESCRIPTION_LOCALE), standardOrder.getOrderNo()))
                .amount(amount)
                .paymentReceiverType(PaymentReceiverType.REFUND)
                .status(PaymentStatus.SUCCESSFUL)
                .build();
        payment = dataSource.save(payment);

        Refund refund = Refund.builder()
                .standardOrder(standardOrder)
                .staff(staff)
                .standardOrder(standardOrder)
                .amount(amount)
                .proofImage(proofImage)
                .reason(input.refundStandardOrderRequestData().reason())
                .method(aEnumMapper.toRefundMethod(input.refundStandardOrderRequestData().refundMethod()))
                .payment(payment)
                .build();
        refund = dataSource.save(refund);

        if (standardOrder.getStatus() == StandardOrderStatus.PAID && standardOrder.getTransportationOrders() != null) {
            Set<TransportationOrder> transportationOrders = standardOrder.getTransportationOrders();
            for (var transportationOrder : transportationOrders) {
                transportationOrder.setState(State.INACTIVE);
                dataSource.save(transportationOrder);
            }
        }
        standardOrder.setStatus(StandardOrderStatus.REFUNDED);
        standardOrder = dataSource.save(standardOrder);

        StandardOrderHistory standardOrderHistory = StandardOrderHistory.builder()
                .standardOrder(standardOrder)
                .status(StandardOrderStatus.REFUNDED)
                .build();
        dataSource.save(standardOrderHistory);

        return aRefundStandardOrderMapper.toOutput(refund);
    }
}
