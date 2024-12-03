package com.cplerings.core.api.jewelry.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.jewelry.AJewelry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JewelriesData extends AbstractPaginatedData<AJewelry> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, JewelriesData, AJewelry> {

        @Override
        protected JewelriesData getDataInstance() {
            return new JewelriesData();
        }
    }
}
