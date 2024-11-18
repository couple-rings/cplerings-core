package com.cplerings.core.api.agreement.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.agreement.AAgreement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgreementsData extends AbstractPaginatedData<AAgreement> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, AgreementsData, AAgreement> {

        @Override
        protected AgreementsData getDataInstance() {
            return new AgreementsData();
        }
    }
}
