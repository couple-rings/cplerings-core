package com.cplerings.core.application.shared.entity.design.request;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.design.ADesign;
import com.cplerings.core.domain.account.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

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
}
