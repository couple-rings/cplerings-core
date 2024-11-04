package com.cplerings.core.application.shared.entity.design.request;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.design.ADesign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ACustomRequest {

    private Long id;
    private String comment;
    private ACustomRequestStatus status;
    private AAccount customer;
    private Collection<ADesign> designs = new ArrayList<>();
}
