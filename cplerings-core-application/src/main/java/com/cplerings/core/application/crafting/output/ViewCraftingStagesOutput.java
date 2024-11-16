package com.cplerings.core.application.crafting.output;

import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCraftingStagesOutput extends AbstractPaginatedOutput<ACraftingStage> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewCraftingStagesOutput, ACraftingStage> {

        @Override
        protected ViewCraftingStagesOutput getOutputInstance() {
            return new ViewCraftingStagesOutput();
        }
    }
}
