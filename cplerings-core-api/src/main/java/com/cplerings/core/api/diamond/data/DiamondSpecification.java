package com.cplerings.core.api.diamond.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiamondSpecification extends AbstractPaginatedData<ADiamondSpecification> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, DiamondSpecification, ADiamondSpecification> {

        @Override
        protected DiamondSpecification getDataInstance() {
            return new DiamondSpecification();
        }
    }
}
