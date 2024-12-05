package com.cplerings.core.application.shared.entity.payment;

import java.io.Serializable;
import java.time.Instant;

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
public class ATransaction implements Serializable {

    private Money amount;
    private String bankCode;
    private Instant payDate;
    private String orderInfo;
    private String transactionId;
}
