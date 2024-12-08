package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.contract.AContract;
import com.cplerings.core.application.shared.entity.ring.ARing;
import com.cplerings.core.domain.order.CustomOrderStatus;
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
public class ACustomOrder implements Serializable {

    private Long id;
    private ARing firstRing;
    private ARing secondRing;
    private AAccount customer;
    private AAccount jeweler;
    private AContract contract;
    private Money totalPrice;
    private ACustomOrderStatus status;
    private String orderNo;
    private Instant createdAt;
    private Collection<ACustomOrderHistory> customOrderHistories;
    private ARefundInfo refund;
}
