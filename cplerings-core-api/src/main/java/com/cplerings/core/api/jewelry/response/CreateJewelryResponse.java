package com.cplerings.core.api.jewelry.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateJewelryResponse extends AbstractDataResponse<AJewelry> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CreateJewelryResponse, AJewelry> {

        @Override
        protected CreateJewelryResponse getResponseInstance() {
            return new CreateJewelryResponse();
        }
    }
}
