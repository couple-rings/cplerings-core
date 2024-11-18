package com.cplerings.core.api.order.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.order.ACustomOrderStatus;

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
public class ViewCustomOrdersRequest extends AbstractPaginatedRequest {

    private Long customerId;
    private Long jewelerId;
    private ACustomOrderStatus status;
    private Long branchId;
}
