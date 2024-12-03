package com.cplerings.core.api.design.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;
import com.cplerings.core.application.shared.entity.design.ADesignStatus;

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
public class ViewDesignsRequest extends AbstractPaginatedRequest {

    private ADesignStatus status;
    private Integer size;
    private Long designCollectionId;
    private Long categoryId;
    private Long metalSpecId;
    private ADesignCharacteristic characteristic;
    private String name;
}
