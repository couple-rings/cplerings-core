package com.cplerings.core.api.design.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.ADesignVersion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignVersionsData extends AbstractPaginatedData<ADesignVersion> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, DesignVersionsData, ADesignVersion> {

        @Override
        protected DesignVersionsData getDataInstance() {
            return new DesignVersionsData();
        }
    }
}
