package com.cplerings.core.api.jewelry.response;

import com.cplerings.core.api.jewelry.data.JewelryCategoriesData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewJewelryCategoriesResponse extends AbstractPaginatedResponse<JewelryCategoriesData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewJewelryCategoriesResponse, JewelryCategoriesData> {

        @Override
        protected ViewJewelryCategoriesResponse getResponseInstance() {
            return new ViewJewelryCategoriesResponse();
        }
    }
}
