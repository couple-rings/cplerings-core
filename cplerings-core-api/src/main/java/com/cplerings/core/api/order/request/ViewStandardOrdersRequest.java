package com.cplerings.core.api.order.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.order.AStandardOrderStatus;

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
public class ViewStandardOrdersRequest extends AbstractPaginatedRequest {

    private Long branchId;
    private AStandardOrderStatus status;
    private Long customerId;
}
