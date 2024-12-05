package com.cplerings.core.application.shared.entity.order;

import java.io.Serializable;

import com.cplerings.core.application.shared.entity.branch.ABranch;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;

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
public class AStandardOrderItem implements Serializable {

    private Long id;
    private AJewelry jewelry;
    private ABranch branch;
    private ADesign design;
    private AMetalSpecification metalSpecification;
}
