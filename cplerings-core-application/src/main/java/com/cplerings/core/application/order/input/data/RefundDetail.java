package com.cplerings.core.application.order.input.data;

import com.cplerings.core.application.shared.entity.order.ARefundMethod;

import lombok.Builder;

@Builder
public record RefundDetail(Long staffId, String reason, Long proofImageId, ARefundMethod method) {

}
