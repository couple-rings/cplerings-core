package com.cplerings.core.api.craftingrequest.response;

import com.cplerings.core.api.craftingrequest.data.CustomOrderCraftingRequestData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptCraftingRequestResponse extends AbstractDataResponse<CustomOrderCraftingRequestData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, AcceptCraftingRequestResponse, CustomOrderCraftingRequestData> {

        @Override
        protected AcceptCraftingRequestResponse getResponseInstance() {
            return new AcceptCraftingRequestResponse();
        }
    }
}
