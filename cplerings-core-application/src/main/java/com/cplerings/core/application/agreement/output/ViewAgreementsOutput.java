package com.cplerings.core.application.agreement.output;

import com.cplerings.core.application.shared.entity.agreement.AAgreement;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewAgreementsOutput extends AbstractPaginatedOutput<AAgreement> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewAgreementsOutput, AAgreement> {

        @Override
        protected ViewAgreementsOutput getOutputInstance() {
            return new ViewAgreementsOutput();
        }
    }
}
