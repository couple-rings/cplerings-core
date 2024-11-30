package com.cplerings.core.application.shared.entity.design.request;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.design.ADesign;
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
public class ACustomRequest implements Serializable {

    private Long id;
    private String comment;
    private ACustomRequestStatus status;
    private AAccount customer;
    private AAccount staff;
    private Set<ADesign> designs;
    private Collection<ACustomRequestHistory> customRequestHistories;
    private Instant createdAt;
    private Money designFee;
}
