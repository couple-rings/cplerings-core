package com.cplerings.core.api.crafting.response;

import com.cplerings.core.api.crafting.data.CraftingStagesData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewCraftingStagesResponse extends AbstractPaginatedResponse<CraftingStagesData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewCraftingStagesResponse, CraftingStagesData> {

        @Override
        protected ViewCraftingStagesResponse getResponseInstance() {
            return new ViewCraftingStagesResponse();
        }
    }
}
