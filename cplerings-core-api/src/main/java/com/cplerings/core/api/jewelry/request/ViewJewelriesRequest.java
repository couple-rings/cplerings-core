package com.cplerings.core.api.jewelry.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.jewelry.AJewelryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class ViewJewelriesRequest extends AbstractPaginatedRequest {

    private Long branchId;
    private Long designId;
    private Long metalSpecId;
    private AJewelryStatus status;
}
