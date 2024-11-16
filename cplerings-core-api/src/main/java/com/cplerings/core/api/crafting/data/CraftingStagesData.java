package com.cplerings.core.api.crafting.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CraftingStagesData extends AbstractPaginatedData<ACraftingStage> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, CraftingStagesData, ACraftingStage> {

        @Override
        protected CraftingStagesData getDataInstance() {
            return new CraftingStagesData();
        }
    }
}
