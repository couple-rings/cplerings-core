package com.cplerings.core.api.contract.response;

import com.cplerings.core.api.contract.data.Contract;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetermineContractResponse extends AbstractDataResponse<Contract> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, DetermineContractResponse, Contract> {

        @Override
        protected DetermineContractResponse getResponseInstance() {
            return new DetermineContractResponse();
        }
    }
}
