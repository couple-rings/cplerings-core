package com.cplerings.core.application.order.implementation;

import static com.cplerings.core.application.order.error.PayStandardOrderErrorCode.ADDRESS_NOT_FOUND;
import static com.cplerings.core.application.order.error.PayStandardOrderErrorCode.INVALID_STANDARD_ORDER_ID;
import static com.cplerings.core.application.order.error.PayStandardOrderErrorCode.JEWELRY_NOT_IN_STOCK;
import static com.cplerings.core.application.order.error.PayStandardOrderErrorCode.STANDARD_ORDER_ID_REQUIRED;
import static com.cplerings.core.application.order.error.PayStandardOrderErrorCode.STANDARD_ORDER_NOT_FOUND;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cplerings.core.application.order.PayStandardOrderUseCase;
import com.cplerings.core.application.order.datasource.PayStandardOrderDataSource;
import com.cplerings.core.application.order.datasource.data.JewelrySearchInfo;
import com.cplerings.core.application.order.input.PayStandardOrderInput;
import com.cplerings.core.application.order.output.PayStandardOrderOutput;
import com.cplerings.core.application.shared.service.payment.PaymentInfo;
import com.cplerings.core.application.shared.service.payment.PaymentRequest;
import com.cplerings.core.application.shared.service.payment.PaymentRequestService;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.number.NumberUtils;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.jewelry.JewelryStatus;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportStatus;
import com.cplerings.core.domain.order.TransportationOrder;
import com.cplerings.core.domain.payment.PaymentReceiverType;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class PayStandardOrderUseCaseImpl extends AbstractUseCase<PayStandardOrderInput, PayStandardOrderOutput>
        implements PayStandardOrderUseCase {

    private final PayStandardOrderDataSource dataSource;
    private final SecurityService securityService;
    private final PaymentRequestService paymentRequestService;

    @Override
    protected void validateInput(UseCaseValidator validator, PayStandardOrderInput input) {
        super.validateInput(validator, input);
        validator.validate(input.standardOrderId() != null, STANDARD_ORDER_ID_REQUIRED);
        validator.clearAndThrowErrorCodes();

        validator.validate(NumberUtils.isPositive(input.standardOrderId()), INVALID_STANDARD_ORDER_ID);
    }

    @Override
    protected PayStandardOrderOutput internalExecute(UseCaseValidator validator, PayStandardOrderInput input) {
        final CurrentUser currentUser = securityService.getCurrentUser();
        final StandardOrder standardOrder = dataSource.findStandardOrderByIdAndCustomerId(input.standardOrderId(), currentUser.id())
                .orElse(null);
        validator.validateAndStopExecution(standardOrder != null, STANDARD_ORDER_NOT_FOUND);
        Collection<StandardOrderItem> standardOrderItems = standardOrder.getStandardOrderItems();
        final Map<JewelryGroup, List<StandardOrderItem>> jewelryGroupStandardOrderItems = new HashMap<>();
        final Map<JewelryGroup, Integer> jewelryGroups = new HashMap<>();
        for (StandardOrderItem item : standardOrderItems) {
            final JewelryGroup jewelryGroup = JewelryGroup.builder()
                    .metalSpecificationId(item.getMetalSpecification().getId())
                    .designId(item.getDesign().getId())
                    .branchId(item.getBranch().getId())
                    .build();
            if (jewelryGroups.containsKey(jewelryGroup)) {
                jewelryGroups.computeIfPresent(jewelryGroup, (key, value) -> value + 1);
                jewelryGroupStandardOrderItems.get(jewelryGroup).add(item);
            } else {
                jewelryGroups.put(jewelryGroup, 1);
                jewelryGroupStandardOrderItems.put(jewelryGroup, new ArrayList<>(List.of(item)));
            }
        }
        final Collection<JewelrySearchInfo> jewelrySearchInfos = jewelryGroups.entrySet()
                .stream()
                .map(entry -> JewelrySearchInfo.builder()
                        .branchId(entry.getKey().branchId())
                        .metalSpecificationId(entry.getKey().metalSpecificationId())
                        .designId(entry.getKey().designId())
                        .count(entry.getValue())
                        .build())
                .collect(Collectors.toSet());
        for (JewelrySearchInfo info : jewelrySearchInfos) {
            final List<Jewelry> jewelries = dataSource.getJewelries(info);
            final JewelryGroup jewelryGroup = JewelryGroup.builder()
                    .metalSpecificationId(info.metalSpecificationId())
                    .designId(info.designId())
                    .branchId(info.branchId())
                    .build();
            final List<StandardOrderItem> items = jewelryGroupStandardOrderItems.get(jewelryGroup);
            validator.validateAndStopExecution(jewelries.size() == items.size(), JEWELRY_NOT_IN_STOCK);
            for (int i = 0; i < jewelries.size(); i++) {
                final StandardOrderItem item = items.get(i);
                final Jewelry jewelry = jewelries.get(i);
                item.setJewelry(jewelry);
                jewelry.setStatus(JewelryStatus.UNAVAILABLE);
            }
            dataSource.save(jewelries);
        }
        dataSource.save(standardOrderItems);

        final PaymentRequest paymentRequest = paymentRequestService.requestPayment(PaymentInfo.builder()
                .receiverType(PaymentReceiverType.STANDARD)
                .amount(standardOrder.getTotalPrice())
                .description("Standard Order " + standardOrder.getOrderNo())
                .build());
        standardOrder.setPayment(paymentRequest.getPayment());
        StandardOrder standardOrderCreated = dataSource.save(standardOrder);

        // Create TransportationOrder
        if (input.transportationAddressId() != null) {
            TransportationAddress address = dataSource.getTransportationAddressById(input.transportationAddressId())
                    .orElse(null);
            validator.validateAndStopExecution(address != null, ADDRESS_NOT_FOUND);
            TransportationOrder transportationOrder = TransportationOrder.builder()
                    .status(TransportStatus.PENDING)
                    .receiverName(address.getReceiverName())
                    .receiverPhone(address.getReceiverPhone())
                    .deliveryAddress(address.getAddressAsString())
                    .standardOrder(standardOrderCreated)
                    .build();
            transportationOrder = dataSource.save(transportationOrder);
            TransportOrderHistory transportOrderHistory = TransportOrderHistory.builder()
                    .transportationOrder(transportationOrder)
                    .status(TransportStatus.PENDING)
                    .build();
            dataSource.save(transportOrderHistory);
        }

        return PayStandardOrderOutput.builder()
                .paymentId(paymentRequest.getPayment().getId())
                .paymentLink(paymentRequest.getPaymentLink())
                .build();
    }

    @Builder
    private record JewelryGroup(Long metalSpecificationId, Long designId, Long branchId) {

    }
}
