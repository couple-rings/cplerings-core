package com.cplerings.core.api.account.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.account.ADesignStaff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesignStaffsData extends AbstractPaginatedData<ADesignStaff> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, DesignStaffsData, ADesignStaff> {

        @Override
        protected DesignStaffsData getDataInstance() {
            return new DesignStaffsData();
        }
    }
}
