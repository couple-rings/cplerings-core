package com.cplerings.core.api.account.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.account.AAccount;

public class TransportersData extends AbstractPaginatedData<AAccount> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, TransportersData, AAccount> {

        @Override
        protected TransportersData getDataInstance() {
            return new TransportersData();
        }
    }
}
