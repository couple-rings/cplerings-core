package com.cplerings.core.api.crafting.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.crafting.ACraftingRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CraftingRequestData extends AbstractPaginatedData<ACraftingRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, CraftingRequestData, ACraftingRequest> {

        @Override
        protected CraftingRequestData getDataInstance() {
            return new CraftingRequestData();
        }
    }
}
