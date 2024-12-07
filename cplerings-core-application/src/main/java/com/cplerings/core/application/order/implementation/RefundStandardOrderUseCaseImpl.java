package com.cplerings.core.application.order.implementation;

import java.math.BigDecimal;

import com.cplerings.core.application.order.RefundStandardOrderUseCase;
import com.cplerings.core.application.order.datasource.RefundStandardOrderDataSource;
import com.cplerings.core.application.order.error.RefundStandardOrderErrorCode;
import com.cplerings.core.application.order.input.RefundStandardOrderInput;
import com.cplerings.core.application.order.mapper.ARefundStandardOrderMapper;
import com.cplerings.core.application.order.output.RefundStandardOrderOutput;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.refund.RefundMethod;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCaseImplementation
public class RefundStandardOrderUseCaseImpl extends AbstractUseCase<RefundStandardOrderInput, RefundStandardOrderOutput> implements RefundStandardOrderUseCase {

    private final RefundStandardOrderDataSource refundStandardOrderDataSource;
    private final ARefundStandardOrderMapper aRefundStandardOrderMapper;
    private final ConfigurationService configurationService;

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
        StandardOrder standardOrder = refundStandardOrderDataSource.getStandardOrderWithOrderItem(input.standardOrderId())
                .orElse(null);
        validator.validateAndStopExecution(standardOrder != null, RefundStandardOrderErrorCode.STANDARD_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(standardOrder.getStatus() == StandardOrderStatus.PAID || standardOrder.getStatus() == StandardOrderStatus.COMPLETED, RefundStandardOrderErrorCode.WRONG_STATUS_FOR_REFUNDED);
        Account staff = refundStandardOrderDataSource.getStaffById(input.refundStandardOrderRequestData().staffId())
                .orElse(null);
        validator.validateAndStopExecution(staff != null, RefundStandardOrderErrorCode.STAFF_NOT_FOUND);
        Image proofImage = refundStandardOrderDataSource.getImageById(input.refundStandardOrderRequestData().proofImageId())
                        .orElse(null);
        validator.validateAndStopExecution(proofImage != null, RefundStandardOrderErrorCode.IMAGE_NOT_FOUND);
        standardOrder.getStandardOrderItems().forEach(standardOrderItem -> {
            Jewelry jewelry = standardOrderItem.getJewelry();
            jewelry.setStatus(JewelryStatus.AVAILABLE);
            refundStandardOrderDataSource.save(jewelry);
        });
        BigDecimal refundPercentage = BigDecimal.valueOf(configurationService.getRefundPercentage());
        BigDecimal amount = standardOrder.getTotalPrice().getAmount().multiply(refundPercentage).divide(BigDecimal.valueOf(100));
        Refund refund = Refund.builder()
                .standardOrder(standardOrder)
                .staff(staff)
                .standardOrder(standardOrder)
                .amount(Money.create(amount))
                .proofImage(proofImage)
                .reason(input.refundStandardOrderRequestData().reason())
                .build();
        switch (input.refundStandardOrderRequestData().refundMethod()) {
            case CASH -> refund.setMethod(RefundMethod.CASH);
            case TRANSFER -> refund.setMethod(RefundMethod.TRANSFER);
        }
        refund = refundStandardOrderDataSource.save(refund);
        return aRefundStandardOrderMapper.toOutput(refund);
    }
}
