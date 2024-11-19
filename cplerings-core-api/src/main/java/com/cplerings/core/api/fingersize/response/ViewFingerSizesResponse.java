package com.cplerings.core.api.fingersize.response;

import com.cplerings.core.api.fingersize.data.FingerSizesData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewFingerSizesResponse extends AbstractPaginatedResponse<FingerSizesData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewFingerSizesResponse, FingerSizesData> {

        @Override
        protected ViewFingerSizesResponse getResponseInstance() {
            return new ViewFingerSizesResponse();
        }
    }
}
