package com.cplerings.core.api.account.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.account.AAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetRandomStaffResponse extends AbstractDataResponse<AAccount> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, GetRandomStaffResponse, AAccount> {

        @Override
        protected GetRandomStaffResponse getResponseInstance() {
            return new GetRandomStaffResponse();
        }
    }
}
