package com.cplerings.core.api.transport.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.transport.ATransportationOrderStatus;

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
public class ViewTransportationOrdersRequest extends AbstractPaginatedRequest {

    private Long transporterId;
    private Long branchId;
    private ATransportationOrderStatus status;
}
