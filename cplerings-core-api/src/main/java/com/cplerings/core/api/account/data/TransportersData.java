package com.cplerings.core.api.account.data;

import com.cplerings.core.api.design.data.CustomRequestsData;
import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;

public class TransportersData extends AbstractPaginatedData<AAccount> {

    public static CustomRequestsData.Builder builder() {
        return new CustomRequestsData.Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<CustomRequestsData.Builder, CustomRequestsData, ACustomRequest> {

        @Override
        protected CustomRequestsData getDataInstance() {
            return new CustomRequestsData();
        }
    }
}
