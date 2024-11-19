package com.cplerings.core.api.fingersize.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.fingersize.data.FingerSizesData;
import com.cplerings.core.api.fingersize.request.ViewFingerSizesRequest;
import com.cplerings.core.api.fingersize.response.ViewFingerSizesResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.fingersize.input.ViewFingerSizesInput;
import com.cplerings.core.application.fingersize.output.ViewFingerSizesOutput;
import com.cplerings.core.application.shared.entity.ring.AFingerSize;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewFingerSizesMapper extends APIPaginatedMapper<ViewFingerSizesInput, ViewFingerSizesOutput, FingerSizesData, AFingerSize, ViewFingerSizesRequest, ViewFingerSizesResponse> {
}
