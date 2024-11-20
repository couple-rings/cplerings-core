package com.cplerings.core.api.diamond.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.ADiamond;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiamondsData extends AbstractPaginatedData<ADiamond> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, DiamondsData, ADiamond> {

        @Override
        protected DiamondsData getDataInstance() {
            return new DiamondsData();
        }
    }
}
