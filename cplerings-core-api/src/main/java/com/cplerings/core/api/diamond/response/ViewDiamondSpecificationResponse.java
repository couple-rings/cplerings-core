package com.cplerings.core.api.diamond.response;

import com.cplerings.core.api.diamond.data.DiamondSpecification;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewDiamondSpecificationResponse extends AbstractPaginatedResponse<DiamondSpecification> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewDiamondSpecificationResponse, DiamondSpecification> {

        @Override
        protected ViewDiamondSpecificationResponse getResponseInstance() {
            return new ViewDiamondSpecificationResponse();
        }
    }
}
