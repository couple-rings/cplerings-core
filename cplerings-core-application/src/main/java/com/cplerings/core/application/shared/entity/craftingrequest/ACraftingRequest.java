package com.cplerings.core.application.shared.entity.craftingrequest;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.domain.design.crafting.CraftingRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ACraftingRequest implements Serializable {

    private Long id;
    private AAccount customer;
    private AMetalSpecification metalSpecification;
    private ADiamondSpecification diamondSpecification;
    private AAccount reviewer;
    private String engraving;
    private Integer fingerSize;
    private String comment;
    private CraftingRequestStatus craftingRequestStatus;
    private Instant createdAt;
}
