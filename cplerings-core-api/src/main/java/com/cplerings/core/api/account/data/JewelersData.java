package com.cplerings.core.api.account.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.application.shared.entity.account.AJeweler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JewelersData extends AbstractPaginatedData<AJeweler> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, JewelersData, AJeweler> {

        @Override
        protected JewelersData getDataInstance() {
            return new JewelersData();
        }
    }
}
