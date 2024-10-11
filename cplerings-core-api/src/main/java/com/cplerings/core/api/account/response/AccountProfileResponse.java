package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.AccountProfile;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountProfileResponse extends AbstractDataResponse<AccountProfile>{

    public static Builder builder() { return new Builder();}

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, AccountProfileResponse, AccountProfile> {

        @Override
        protected AccountProfileResponse getResponseInstance() {
            return new AccountProfileResponse();
        }
    }
}
