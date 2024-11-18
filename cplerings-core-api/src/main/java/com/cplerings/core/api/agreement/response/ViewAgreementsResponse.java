package com.cplerings.core.api.agreement.response;

import com.cplerings.core.api.agreement.data.AgreementsData;
import com.cplerings.core.api.metal.data.MetalSpecification;
import com.cplerings.core.api.metal.response.ViewMetalSpecificationResponse;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewAgreementsResponse extends AbstractPaginatedResponse<AgreementsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewAgreementsResponse, AgreementsData> {

        @Override
        protected ViewAgreementsResponse getResponseInstance() {
            return new ViewAgreementsResponse();
        }
    }
}
