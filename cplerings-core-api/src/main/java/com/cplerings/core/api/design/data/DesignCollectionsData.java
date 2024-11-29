package com.cplerings.core.api.design.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.ADesignCollection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesignCollectionsData extends AbstractPaginatedData<ADesignCollection> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, DesignCollectionsData, ADesignCollection> {

        @Override
        protected DesignCollectionsData getDataInstance() {
            return new DesignCollectionsData();
        }
    }
}
