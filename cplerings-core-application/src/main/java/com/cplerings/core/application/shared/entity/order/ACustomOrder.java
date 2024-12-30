package com.cplerings.core.application.shared.entity.order;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.contract.AContract;
import com.cplerings.core.application.shared.entity.ring.ARing;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ACustomOrder implements Serializable {

    private Long id;
    private ARing firstRing;
    private ARing secondRing;
    private AAccount customer;
    private AAccount jeweler;
    private AContract contract;
    private Money shippingFee;
    private Money totalPrice;
    private BigDecimal priceApplicationRatio;
    private ACustomOrderStatus status;
    private String orderNo;
    private Instant createdAt;
    private Collection<ACustomOrderHistory> customOrderHistories;
    private ARefundInfo refund;
}
