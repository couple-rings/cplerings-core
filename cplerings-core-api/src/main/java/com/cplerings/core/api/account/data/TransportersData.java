package com.cplerings.core.api.account.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.account.ATransporter;

public class TransportersData extends AbstractPaginatedData<ATransporter> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, TransportersData, ATransporter> {

        @Override
        protected TransportersData getDataInstance() {
            return new TransportersData();
        }
    }
}
