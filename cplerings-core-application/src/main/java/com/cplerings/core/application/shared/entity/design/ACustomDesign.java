package com.cplerings.core.application.shared.entity.design;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.spouse.ASpouse;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.valueobject.Weight;
import com.cplerings.core.domain.spouse.Spouse;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class ACustomDesign implements Serializable {

    private Long id;
    private ADesignVersion designVersion;
    private ASpouse spouse;
    private AAccount account;
    private BigDecimal metalWeight;
    private int sideDiamondsCount;
}
