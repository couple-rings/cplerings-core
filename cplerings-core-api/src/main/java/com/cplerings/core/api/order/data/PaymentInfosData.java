package com.cplerings.core.api.order.data;

import com.cplerings.core.application.shared.entity.payment.APaymentInfo;

import lombok.Builder;

import java.util.List;

@Builder
public record PaymentInfosData(List<APaymentInfo> payments) {

}
