package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.AccountData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse extends AbstractDataResponse<AccountData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, AccountResponse, AccountData> {

        @Override
        protected AccountResponse getResponseInstance() {
            return new AccountResponse();
        }
    }
}
