package com.cplerings.core.api.design.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.ADesign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesignsData extends AbstractPaginatedData<ADesign> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, DesignsData, ADesign> {

        @Override
        protected DesignsData getDataInstance() {
            return new DesignsData();
        }
    }
}
