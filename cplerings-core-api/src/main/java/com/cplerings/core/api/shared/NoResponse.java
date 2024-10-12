package com.cplerings.core.api.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NoResponse extends AbstractDataResponse<NoData> {

    public static final NoResponse INSTANCE = new Builder().build();

    private static final class Builder extends AbstractDataResponseBuilder<Builder, NoResponse, NoData> {

        @Override
        public NoResponse build() {
            final NoResponse response = super.build();
            response.setType(Type.INFO);
            response.setData(NoData.INSTANCE);
            return response;
        }

        @Override
        protected NoResponse getResponseInstance() {
            return new NoResponse();
        }
    }
}
