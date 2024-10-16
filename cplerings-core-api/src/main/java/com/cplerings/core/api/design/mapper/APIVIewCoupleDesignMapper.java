package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.CoupleDesignInformation;
import com.cplerings.core.api.design.request.ViewDesignCouplesRequest;
import com.cplerings.core.api.design.response.ViewCoupleDesignResponse;
import com.cplerings.core.api.mapper.APIPaginatedMapper;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIVIewCoupleDesignMapper extends APIPaginatedMapper<ViewCoupleDesignInput, ViewCoupleDesignOutput, CoupleDesignInformation, ViewDesignCouplesRequest, ViewCoupleDesignResponse> {
}
