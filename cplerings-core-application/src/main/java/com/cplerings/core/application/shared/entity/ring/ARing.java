package com.cplerings.core.application.shared.entity.ring;

import com.cplerings.core.application.shared.entity.design.ACustomDesign;
import com.cplerings.core.application.shared.entity.file.ADocument;
import com.cplerings.core.application.shared.entity.spouse.ASpouse;
import com.cplerings.core.domain.ring.RingStatus;

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
public class ARing implements Serializable {

    private Long id;
    private Instant purchaseDate;
    private RingStatus status;
    private Instant maintenanceExpiredDate;
    private ADocument maintenanceDocument;
    private ASpouse spouse;
    private Instant createdAt;
    private ACustomDesign customDesign;
}
