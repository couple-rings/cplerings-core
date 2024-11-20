package com.cplerings.core.api.diamond.response;

import com.cplerings.core.api.diamond.data.DiamondsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDiamondsResponse extends AbstractPaginatedResponse<DiamondsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewDiamondsResponse, DiamondsData> {

        @Override
        protected ViewDiamondsResponse getResponseInstance() {
            return new ViewDiamondsResponse();
        }
    }
}
