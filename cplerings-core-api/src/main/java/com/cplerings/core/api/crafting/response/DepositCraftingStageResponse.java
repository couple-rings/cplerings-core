package com.cplerings.core.api.crafting.response;

import com.cplerings.core.api.crafting.data.CraftingStagePaymentLinkData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepositCraftingStageResponse extends AbstractDataResponse<CraftingStagePaymentLinkData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, DepositCraftingStageResponse, CraftingStagePaymentLinkData> {

        @Override
        protected DepositCraftingStageResponse getResponseInstance() {
            return new DepositCraftingStageResponse();
        }
    }
}
