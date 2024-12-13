package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.CUSTOM_ORDER_ID_REQUIRED;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.CUSTOM_ORDER_NOT_COMPLETED;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.CUSTOM_ORDER_NOT_FOUND;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.CUSTOM_ORDER_NOT_RECEIVED_BY_CUSTOMER;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.INVALID_CUSTOM_ORDER_ID;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.INVALID_PROOF_IMAGE_ID;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.INVALID_STAFF_ID;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.PROOF_IMAGE_ID_REQUIRED;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.PROOF_IMAGE_NOT_FOUND;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.REASON_REQUIRED;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.REFUND_DETAIL_REQUIRED;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.REFUND_METHOD_REQUIRED;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.STAFF_ID_REQUIRED;
import static com.cplerings.core.application.order.error.RefundCustomOrderErrorCode.STAFF_NOT_FOUND;

import com.cplerings.core.application.order.RefundCustomOrderUseCase;
import com.cplerings.core.application.order.datasource.RefundCustomOrderDataSource;
import com.cplerings.core.application.order.input.RefundCustomOrderInput;
import com.cplerings.core.application.order.input.data.RefundDetail;
import com.cplerings.core.application.order.output.RefundCustomOrderOutput;
import com.cplerings.core.application.shared.mapper.AEnumMapper;
import com.cplerings.core.application.shared.mapper.ARefundInfoMapper;
import com.cplerings.core.application.shared.service.configuration.ConfigurationService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.constant.Constants;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.Design;
import com.cplerings.core.domain.design.DesignStatus;
import com.cplerings.core.domain.diamond.Diamond;
import com.cplerings.core.domain.file.Image;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.StandardOrderStatus;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.refund.Refund;
import com.cplerings.core.domain.ring.Ring;
import com.cplerings.core.domain.ring.RingDiamond;
import com.cplerings.core.domain.ring.RingStatus;
import com.cplerings.core.domain.shared.State;
import com.cplerings.core.domain.shared.valueobject.Money;
import com.cplerings.core.domain.spouse.Agreement;

import lombok.RequiredArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@UseCaseImplementation
@RequiredArgsConstructor
public class RefundCustomOrderUseCaseImpl extends AbstractUseCase<RefundCustomOrderInput, RefundCustomOrderOutput>
        implements RefundCustomOrderUseCase {

    private final RefundCustomOrderDataSource dataSource;
    private final AEnumMapper aEnumMapper;
    private final ARefundInfoMapper aRefundInfoMapper;
    private final ConfigurationService configurationService;

    @Override
    protected void validateInput(UseCaseValidator validator, RefundCustomOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.customOrderId() != null, CUSTOM_ORDER_ID_REQUIRED);
        validator.validate(input.refundDetail() != null, REFUND_DETAIL_REQUIRED);
        validator.clearAndThrowErrorCodes();

        final RefundDetail refundDetail = input.refundDetail();
        validator.validate(refundDetail.staffId() != null, STAFF_ID_REQUIRED);
        validator.validate(StringUtils.isNotBlank(refundDetail.reason()), REASON_REQUIRED);
        validator.validate(refundDetail.proofImageId() != null, PROOF_IMAGE_ID_REQUIRED);
        validator.validate(refundDetail.method() != null, REFUND_METHOD_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.customOrderId()), INVALID_CUSTOM_ORDER_ID);
        validator.validate(NumberUtils.isPositive(refundDetail.staffId()), INVALID_STAFF_ID);
        validator.validate(NumberUtils.isPositive(refundDetail.proofImageId()), INVALID_PROOF_IMAGE_ID);
    }

    @Override
    protected RefundCustomOrderOutput internalExecute(UseCaseValidator validator, RefundCustomOrderInput input) {
        CustomOrder customOrder = dataSource.findCustomOrderById(input.customOrderId())
                .orElse(null);
        validator.validateAndStopExecution(customOrder != null, CUSTOM_ORDER_NOT_FOUND);
        validator.validateAndStopExecution(customOrder.getStatus() == CustomOrderStatus.COMPLETED || customOrder.getStatus() == CustomOrderStatus.DONE, CUSTOM_ORDER_NOT_COMPLETED);
        validator.validateAndStopExecution(customOrderIsReceived(customOrder), CUSTOM_ORDER_NOT_RECEIVED_BY_CUSTOMER);

        final RefundDetail refundDetail = input.refundDetail();
        final Account staff = dataSource.findStaffById(refundDetail.staffId())
                .orElse(null);
        validator.validateAndStopExecution(staff != null, STAFF_NOT_FOUND);

        final Image proofImage = dataSource.findImageById(refundDetail.proofImageId())
                .orElse(null);
        validator.validateAndStopExecution(proofImage != null, PROOF_IMAGE_NOT_FOUND);
        if (customOrder.getStatus() == CustomOrderStatus.DONE && customOrder.getTransportationOrders() != null) {
            Set<TransportationOrder> transportationOrders = customOrder.getTransportationOrders();
            for (var transportationOrder : transportationOrders) {
                transportationOrder.setState(State.INACTIVE);
                dataSource.save(transportationOrder);
            }
        }
        customOrder.setStatus(CustomOrderStatus.REFUNDED);
        customOrder = dataSource.save(customOrder);

        final Collection<Ring> rings = Arrays.asList(customOrder.getFirstRing(), customOrder.getSecondRing());
        rings.forEach(ring -> ring.setStatus(RingStatus.REFUNDED));
        dataSource.saveRings(rings);

        final Collection<Design> designs = rings.stream()
                .map(ring -> ring.getCustomDesign().getDesignVersion().getDesign())
                .collect(Collectors.toSet());
        designs.forEach(design -> design.setStatus(DesignStatus.AVAILABLE));
        dataSource.saveDesigns(designs);

        final Collection<RingDiamond> ringDiamonds = rings.stream()
                .flatMap(ring -> ring.getRingDiamonds().stream())
                .collect(Collectors.toSet());
        dataSource.deleteRingDiamonds(ringDiamonds);

        final Collection<Diamond> diamonds = ringDiamonds.stream()
                .map(RingDiamond::getDiamond)
                .collect(Collectors.toSet());
        diamonds.forEach(diamond -> diamond.setState(State.ACTIVE));
        dataSource.saveDiamonds(diamonds);

        final Agreement agreement = Optional.ofNullable(customOrder.getCustomer())
                .map(Account::getAgreement)
                .orElseThrow(() -> new IllegalStateException("No agreement was found"));
        dataSource.delete(agreement);

        Refund refund = Refund.builder()
                .customOrder(customOrder)
                .amount(calculateRefundMoney(customOrder.getTotalPrice()))
                .method(aEnumMapper.toRefundMethod(input.refundDetail().method()))
                .proofImage(proofImage)
                .reason(refundDetail.reason().trim())
                .staff(staff)
                .build();
        refund = dataSource.save(refund);

        final CustomOrderHistory customOrderHistory = CustomOrderHistory.builder()
                .customOrder(customOrder)
                .status(CustomOrderStatus.REFUNDED)
                .build();
        dataSource.save(customOrderHistory);

        return RefundCustomOrderOutput.builder()
                .refundInfo(aRefundInfoMapper.toRefundInfo(refund))
                .build();
    }

    private Money calculateRefundMoney(Money totalPrice) {
        final BigDecimal percentage = BigDecimal.valueOf(configurationService.getRefundPercentage())
                .divide(Constants.ONE_HUNDRED, RoundingMode.HALF_EVEN);
        return totalPrice.multiply(percentage);
    }

    private boolean customOrderIsReceived(CustomOrder customOrder) {
        return CollectionUtils.isEmpty(customOrder.getTransportationOrders())
                || customOrder.getTransportationOrders()
                .stream()
                .filter(Objects::nonNull)
                .anyMatch(transportationOrder -> transportationOrder.getStatus() == TransportStatus.COMPLETED);
    }
}
