package com.cplerings.core.api.jewelry.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetJewelryByProductNoResponse extends AbstractDataResponse<AJewelry> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, GetJewelryByProductNoResponse, AJewelry> {

        @Override
        protected GetJewelryByProductNoResponse getResponseInstance() {
            return new GetJewelryByProductNoResponse();
        }
    }
}
