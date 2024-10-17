package com.cplerings.core.api.shared.mapper;

import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.api.shared.NoResponse;
import com.cplerings.core.application.shared.output.NoOutput;

@SuppressWarnings("java:S119")
public interface APINoResponseMapper<IN, REQ> extends APIMapper<IN, NoOutput, NoData, REQ, NoResponse> {

    @Override
    default NoData toData(NoOutput output) {
        return NoData.INSTANCE;
    }

    @Override
    default NoResponse toResponse(NoData noData) {
        return NoResponse.INSTANCE;
    }
}
