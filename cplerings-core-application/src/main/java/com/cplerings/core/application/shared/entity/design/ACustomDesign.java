package com.cplerings.core.application.shared.entity.design;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.file.ADocument;
import com.cplerings.core.application.shared.entity.spouse.ASpouse;

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
    private ADocument blueprint;
    private List<ADiamondSpecification> diamondSpecifications;
    private List<AMetalSpecification> metalSpecifications;
    private int sideDiamondsCount;
    private Instant createdAt;
}
