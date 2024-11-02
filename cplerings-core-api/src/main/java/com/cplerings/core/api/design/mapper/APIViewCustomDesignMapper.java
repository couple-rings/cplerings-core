package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.design.request.ViewCustomDesignRequest;
import com.cplerings.core.api.design.response.ViewCustomDesignResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.design.input.ViewCustomDesignInput;
import com.cplerings.core.application.design.output.ViewCustomDesignOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomDesignMapper extends APIMapper<ViewCustomDesignInput, ViewCustomDesignOutput, CustomDesignData, ViewCustomDesignRequest, ViewCustomDesignResponse> {
}
