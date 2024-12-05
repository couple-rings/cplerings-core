package com.cplerings.core.application.shared.entity.payment;

import java.io.Serializable;

import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;
import com.cplerings.core.application.shared.entity.order.AStandardOrder;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
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
public class APayment implements Serializable {

    private Long id;
    private PaymentType type;
    private String description;
    private Money amount;
    private PaymentStatus status;
    private PaymentReceiverType paymentReceiverType;
    private ATransaction vnPayTransaction;
    private ACustomRequest customRequest;
    private ACraftingStage craftingStage;
    private AStandardOrder standardOrder;
}
