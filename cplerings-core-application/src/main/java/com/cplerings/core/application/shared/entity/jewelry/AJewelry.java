package com.cplerings.core.application.shared.entity.jewelry;

import java.io.Serializable;
import java.time.Instant;

import com.cplerings.core.application.shared.entity.branch.ABranch;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.application.shared.entity.file.ADocument;

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
public class AJewelry implements Serializable {

    private Long id;
    private AMetalSpecification metalSpecification;
    private ADesign design;
    private ABranch branch;
    private Instant purchaseDate;
    private AJewelryStatus status;
    private ADocument maintenanceDocument;
    private String productNo;
}
