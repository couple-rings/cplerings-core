package com.cplerings.core.api.order.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.order.ARefund;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefundsData extends AbstractPaginatedData<ARefund> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, RefundsData, ARefund> {

        @Override
        protected RefundsData getDataInstance() {
            return new RefundsData();
        }
    }
}
