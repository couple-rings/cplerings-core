package com.cplerings.core.api.crafting.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.crafting.ACraftingRequestStatus;

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
public class ViewCraftingRequestsRequest extends AbstractPaginatedRequest {

    private Long customDesignId;
    private Long customerId;
    private ACraftingRequestStatus status;
}
