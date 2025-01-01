package com.cplerings.core.application.shared.entity.payment;

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
public class AVNPayTransaction implements Serializable {

    private Long id;
    private Money amount;
    private String bankCode;
    private Instant payDate;
    private String orderInfo;
    private String transactionId;
}
