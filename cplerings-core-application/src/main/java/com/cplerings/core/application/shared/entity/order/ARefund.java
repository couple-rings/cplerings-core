package com.cplerings.core.application.shared.entity.order;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.file.AImage;
import com.cplerings.core.application.shared.entity.payment.APaymentInfo;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ARefund implements Serializable {

    private Long id;
    private String reason;
    private ARefundMethod method;
    private Money amount;
    private AAccount staff;
    private AImage proofImage;
    private AStandardOrder standardOrder;
    private ACustomOrder customOrder;
    private APaymentInfo payment;
    private String orderNo;
    private Instant createdAt;
}
