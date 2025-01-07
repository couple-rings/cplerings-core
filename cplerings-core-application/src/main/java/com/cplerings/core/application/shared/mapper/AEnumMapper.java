package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStageStatus;
import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;
import com.cplerings.core.application.shared.entity.design.ADiamondClarity;
import com.cplerings.core.application.shared.entity.design.ADiamondColor;
import com.cplerings.core.application.shared.entity.design.ADiamondShape;
import com.cplerings.core.application.shared.entity.design.AMetalColor;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequestStatus;
import com.cplerings.core.application.shared.entity.order.ACustomOrderStatus;
import com.cplerings.core.application.shared.entity.order.ADifficulty;
import com.cplerings.core.application.shared.entity.order.APaymentMethod;
import com.cplerings.core.application.shared.entity.order.ARefundMethod;
import com.cplerings.core.application.shared.entity.payment.APaymentReceiverType;
import com.cplerings.core.application.shared.entity.payment.APaymentStatus;
import com.cplerings.core.application.shared.entity.payment.APaymentType;
import com.cplerings.core.application.shared.entity.shared.AState;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.crafting.CraftingStageStatus;
import com.cplerings.core.domain.design.DesignCharacteristic;
import com.cplerings.core.domain.design.request.CustomRequestStatus;
import com.cplerings.core.domain.diamond.DiamondClarity;
import com.cplerings.core.domain.diamond.DiamondColor;
import com.cplerings.core.domain.diamond.DiamondShape;
import com.cplerings.core.domain.metal.MetalColor;
import com.cplerings.core.domain.order.CustomOrderStatus;
import com.cplerings.core.domain.order.Difficulty;
import com.cplerings.core.domain.payment.PaymentReceiverType;
import com.cplerings.core.domain.payment.PaymentStatus;
import com.cplerings.core.domain.payment.PaymentType;
import com.cplerings.core.domain.refund.RefundMethod;
import com.cplerings.core.domain.resell.PaymentMethod;
import com.cplerings.core.domain.shared.State;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AEnumMapper {

    ADesignCharacteristic toDesignCharacteristic(DesignCharacteristic designCharacteristic);

    ADiamondClarity toDiamondClarity(DiamondClarity diamondClarity);

    ADiamondColor toDiamondColor(DiamondColor diamondColor);

    ADiamondShape toDiamondShape(DiamondShape diamondShape);

    AMetalColor toMetalColor(MetalColor metalColor);

    ACustomRequestStatus toCustomRequestStatus(CustomRequestStatus customRequestStatus);

    ACraftingStageStatus toCraftingStageStatus(CraftingStageStatus craftingStageStatus);

    ARole toRole(Role role);

    ARefundMethod toRefundMethod(RefundMethod refundMethod);

    RefundMethod toRefundMethod(ARefundMethod refundMethod);

    ACustomOrderStatus toCustomOrderStatus(CustomOrderStatus customOrderStatus);

    APaymentMethod toPaymentMethod(PaymentMethod paymentMethod);

    PaymentMethod toPaymentMethod(APaymentMethod paymentMethod);

    Role toRole(ARole role);

    ADifficulty toDifficulty(Difficulty difficulty);

    Difficulty toDifficulty(ADifficulty difficulty);

    APaymentType toPaymentType(PaymentType paymentType);

    APaymentReceiverType toPaymentReceiverType(PaymentReceiverType paymentReceiverType);

    APaymentStatus toPaymentStatus(PaymentStatus paymentStatus);

    PaymentType toPaymentType(APaymentMethod paymentMethod);

    PaymentType toPaymentType(ARefundMethod refundMethod);

    AState toState(State state);
}
