package com.cplerings.core.application.shared.ring;

import java.io.Serializable;
import java.time.Instant;

import com.cplerings.core.application.shared.entity.design.ADocument;
import com.cplerings.core.application.shared.entity.spouse.ASpouse;
import com.cplerings.core.domain.ring.RingStatus;

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
public class ARing implements Serializable {

    private Long id;
    private Instant purchaseDate;
    private RingStatus status;
    private Instant maintenanceExpiredDate;
    private ADocument maintenanceDocument;
    private ASpouse spouse;
}
