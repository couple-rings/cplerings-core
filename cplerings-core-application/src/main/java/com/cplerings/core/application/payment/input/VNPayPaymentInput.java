package com.cplerings.core.application.payment.input;

import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VNPayPaymentInput {

    private String terminalCode;
    private Money amount;
    private String bankCode;
    private String bankTransferId;
    private String cardType;
    private Instant payDate;
    private String orderInfo;
    private String transactionId;
    private String responseCode;
    private String transactionStatus;
    private Long paymentId;
    private String secureHashType;
    private String secureHash;
}
