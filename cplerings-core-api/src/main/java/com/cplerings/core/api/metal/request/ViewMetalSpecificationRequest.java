package com.cplerings.core.api.metal.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class ViewMetalSpecificationRequest extends AbstractPaginatedRequest {
}
