package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.file.AImage;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
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
public class AResellOrder implements Serializable {

    private Long id;
    private AJewelry jewelry;
    private AAccount customer;
    private AAccount staff;
    private APaymentMethod paymentMethod;
    private Money amount;
    private AImage proofImage;
    private String note;
}
