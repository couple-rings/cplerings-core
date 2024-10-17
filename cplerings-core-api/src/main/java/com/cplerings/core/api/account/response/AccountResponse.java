package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.Account;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse extends AbstractDataResponse<Account> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, AccountResponse, Account> {

        @Override
        protected AccountResponse getResponseInstance() {
            return new AccountResponse();
        }
    }
}
