package com.cplerings.core.api.jewelry.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.AResellOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResellJewelryResponse extends AbstractDataResponse<AResellOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ResellJewelryResponse, AResellOrder> {

        @Override
        protected ResellJewelryResponse getResponseInstance() {
            return new ResellJewelryResponse();
        }
    }
}
