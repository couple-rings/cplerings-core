package com.cplerings.core.application.dashboard.output;

import java.time.Instant;

import com.cplerings.core.application.shared.entity.order.ACustomOrderStatus;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.entity.order.OrderType;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ViewTop5CustomOrderOutput {

    private Money totalPrice;
    private Instant createdAt;
    private OrderType orderType;
    private APaymentMethod paymentMethod;
    private String orderNo;
    private ACustomOrderStatus status;
}
