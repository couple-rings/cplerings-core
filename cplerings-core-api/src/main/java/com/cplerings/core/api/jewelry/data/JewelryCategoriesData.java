package com.cplerings.core.api.jewelry.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.jewelry.AJewelryCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JewelryCategoriesData extends AbstractPaginatedData<AJewelryCategory> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, JewelryCategoriesData, AJewelryCategory> {

        @Override
        protected JewelryCategoriesData getDataInstance() {
            return new JewelryCategoriesData();
        }
    }
}
