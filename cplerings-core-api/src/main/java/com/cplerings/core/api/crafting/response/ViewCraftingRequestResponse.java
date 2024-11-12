package com.cplerings.core.api.crafting.response;

import com.cplerings.core.api.crafting.data.CraftingRequest;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingRequestResponse extends AbstractDataResponse<CraftingRequest>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ViewCraftingRequestResponse, CraftingRequest> {

        @Override
        protected ViewCraftingRequestResponse getResponseInstance() {
            return new ViewCraftingRequestResponse();
        }
    }
}
