package com.cplerings.core.api.diamond.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.design.ADiamond;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDiamondResponse extends AbstractDataResponse<ADiamond> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, UpdateDiamondResponse, ADiamond> {

        @Override
        protected UpdateDiamondResponse getResponseInstance() {
            return new UpdateDiamondResponse();
        }
    }
}
