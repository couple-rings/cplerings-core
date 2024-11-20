package com.cplerings.core.api.diamond.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.design.ADiamond;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class CreateDiamondResponse extends AbstractDataResponse<ADiamond> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CreateDiamondResponse, ADiamond> {

        @Override
        protected CreateDiamondResponse getResponseInstance() {
            return new CreateDiamondResponse();
        }
    }
}
