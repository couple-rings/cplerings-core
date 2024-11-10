package com.cplerings.core.api.design.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomRequestsData extends AbstractPaginatedData<ACustomRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, CustomRequestsData, ACustomRequest> {

        @Override
        protected CustomRequestsData getDataInstance() {
            return new CustomRequestsData();
        }
    }
}
