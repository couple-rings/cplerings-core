package com.cplerings.core.api.crafting.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.account.AAccountCraftingRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingRequestsGroupsData extends AbstractPaginatedData<AAccountCraftingRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, ViewCraftingRequestsGroupsData, AAccountCraftingRequest> {

        @Override
        protected ViewCraftingRequestsGroupsData getDataInstance() {
            return new ViewCraftingRequestsGroupsData();
        }
    }
}
