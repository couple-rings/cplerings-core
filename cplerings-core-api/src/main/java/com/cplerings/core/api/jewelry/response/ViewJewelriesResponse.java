package com.cplerings.core.api.jewelry.response;

import com.cplerings.core.api.jewelry.data.JewelriesData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewJewelriesResponse extends AbstractPaginatedResponse<JewelriesData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewJewelriesResponse, JewelriesData> {

        @Override
        protected ViewJewelriesResponse getResponseInstance() {
            return new ViewJewelriesResponse();
        }
    }
}
