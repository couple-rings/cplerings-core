package com.cplerings.core.application.shared.entity.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APaymentInfo {

    private Long id;
    private APaymentType type;
    private String description;
    private BigDecimal amount;
    private APaymentStatus status;
    private APaymentReceiverType paymentReceiverType;
    private AVNPayTransaction vnPayTransaction;
    private String paymentNo;
}
