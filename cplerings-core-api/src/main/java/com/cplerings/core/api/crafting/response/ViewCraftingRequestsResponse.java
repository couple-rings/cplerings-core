package com.cplerings.core.api.crafting.response;

import com.cplerings.core.api.crafting.data.CraftingRequest;
import com.cplerings.core.api.crafting.data.CraftingRequestData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewCraftingRequestsResponse extends AbstractPaginatedResponse<CraftingRequestData>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponse.AbstractPaginatedResponseBuilder<Builder, ViewCraftingRequestsResponse, CraftingRequestData> {

        @Override
        protected ViewCraftingRequestsResponse getResponseInstance() {
            return new ViewCraftingRequestsResponse();
        }
    }
}
