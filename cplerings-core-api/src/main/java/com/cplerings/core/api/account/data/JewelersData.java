package com.cplerings.core.api.account.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.account.AAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JewelersData extends AbstractPaginatedData<AAccount> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, JewelersData, AAccount> {

        @Override
        protected JewelersData getDataInstance() {
            return new JewelersData();
        }
    }
}
