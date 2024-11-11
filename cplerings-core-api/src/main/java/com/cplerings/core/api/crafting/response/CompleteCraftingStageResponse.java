package com.cplerings.core.api.crafting.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.crafting.ACraftingStage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompleteCraftingStageResponse extends AbstractDataResponse<ACraftingStage> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CompleteCraftingStageResponse, ACraftingStage> {

        @Override
        protected CompleteCraftingStageResponse getResponseInstance() {
            return new CompleteCraftingStageResponse();
        }
    }
}
