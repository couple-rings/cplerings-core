package com.cplerings.core.api.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.account.data.JewelersData;
import com.cplerings.core.api.account.request.ViewJewelersRequest;
import com.cplerings.core.api.account.response.ViewJewelersResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.account.input.ViewJewelersInput;
import com.cplerings.core.application.account.output.ViewJewelersOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewJewelersMapper extends APIMapper<ViewJewelersInput, ViewJewelersOutput, JewelersData, ViewJewelersRequest, ViewJewelersResponse> {
}
