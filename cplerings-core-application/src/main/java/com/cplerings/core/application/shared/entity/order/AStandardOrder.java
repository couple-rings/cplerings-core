package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AStandardOrder implements Serializable {

    private Long id;
    private AAccount customer;
    private Money totalPrice;
    private AStandardOrderStatus status;
    private Collection<AStandardOrderHistory> standardOrderHistories;
    private Collection<AStandardOrderItem> standardOrderItems;
    private Collection<ATransportOrderWithoutStandard> transportationOrders;
    private String orderNo;
    private Instant createdAt;
}
