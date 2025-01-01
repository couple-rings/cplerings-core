package com.cplerings.core.application.order.output;

import com.cplerings.core.application.shared.entity.payment.APaymentInfo;

import lombok.Builder;

import java.util.List;

@Builder
public record ViewCustomOrderPaymentsOutput(List<APaymentInfo> payments) {

}
