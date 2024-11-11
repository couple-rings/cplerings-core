package com.cplerings.core.api.design.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.ACustomDesign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomDesigns extends AbstractPaginatedData<ACustomDesign> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, CustomDesigns, ACustomDesign> {

        @Override
        protected CustomDesigns getDataInstance() {
            return new CustomDesigns();
        }
    }

}
