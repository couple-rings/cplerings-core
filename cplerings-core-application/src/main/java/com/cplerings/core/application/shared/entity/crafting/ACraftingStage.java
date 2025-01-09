package com.cplerings.core.application.shared.entity.crafting;

import com.cplerings.core.application.shared.entity.file.AImage;
import com.cplerings.core.application.shared.entity.order.ACustomOrder;
import com.cplerings.core.application.shared.entity.payment.APaymentInfo;
import com.cplerings.core.domain.shared.valueobject.Money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ACraftingStage {

    private Long id;
    private String name;
    private Long customOrderId;
    private Integer progress;
    private Money amount;
    private Instant completionDate;
    private AImage image;
    private ACraftingStageStatus status;
    private APaymentInfo payment;
    private Collection<ACraftingStageHistory> craftingStageHistories;
    private Instant createdAt;
    private ACustomOrder customOrder;
}
