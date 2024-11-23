package com.cplerings.core.api.account.response;

import com.cplerings.core.api.account.data.UsersData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewUsersResponse extends AbstractDataResponse<UsersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewUsersResponse, UsersData> {

        @Override
        protected ViewUsersResponse getResponseInstance() {
            return new ViewUsersResponse();
        }
    }
}
