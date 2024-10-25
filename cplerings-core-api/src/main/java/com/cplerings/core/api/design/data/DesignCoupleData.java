package com.cplerings.core.api.design.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.ADesignCouple;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignCoupleData extends AbstractPaginatedData<ADesignCouple> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, DesignCoupleData, ADesignCouple> {

        @Override
        protected DesignCoupleData getDataInstance() {
            return new DesignCoupleData();
        }
    }
}
