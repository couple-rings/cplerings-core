package com.cplerings.core.api.design.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.shared.AState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ViewCustomDesignsRequest extends AbstractPaginatedRequest {

    private AState state;
    private Long customerId;
}
