package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.data.DesignCoupleData;
import com.cplerings.core.api.design.request.ViewDesignCouplesRequest;
import com.cplerings.core.api.design.response.ViewCoupleDesignResponse;
import com.cplerings.core.api.shared.mapper.APIPaginatedMapper;
import com.cplerings.core.application.design.input.ViewCoupleDesignInput;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.application.shared.entity.design.ADesignCouple;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIVIewCoupleDesignMapper extends APIPaginatedMapper<ViewCoupleDesignInput, ViewCoupleDesignOutput, DesignCoupleData, ADesignCouple, ViewDesignCouplesRequest, ViewCoupleDesignResponse> {

}
