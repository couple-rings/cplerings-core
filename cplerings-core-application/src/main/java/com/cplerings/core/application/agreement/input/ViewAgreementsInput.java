package com.cplerings.core.application.agreement.input;

import com.cplerings.core.application.diamond.input.ViewDiamondSpecificationInput;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewAgreementsInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewAgreementsInput> {

        @Override
        protected ViewAgreementsInput getRequestInstance() {
            return new ViewAgreementsInput();
        }
    }
}
