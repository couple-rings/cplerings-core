package com.cplerings.core.api.crafting.response;

import com.cplerings.core.api.crafting.data.ViewCraftingRequestsGroupsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewCraftingRequestsGroupsResponse extends AbstractPaginatedResponse<ViewCraftingRequestsGroupsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewCraftingRequestsGroupsResponse, ViewCraftingRequestsGroupsData> {

        @Override
        protected ViewCraftingRequestsGroupsResponse getResponseInstance() {
            return new ViewCraftingRequestsGroupsResponse();
        }
    }
}
