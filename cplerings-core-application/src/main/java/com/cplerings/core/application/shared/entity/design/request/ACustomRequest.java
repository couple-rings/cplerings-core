package com.cplerings.core.application.shared.entity.design.request;

import java.io.Serializable;
import java.util.Set;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.design.ADesign;

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
    private Set<ADesign> designs;
}
