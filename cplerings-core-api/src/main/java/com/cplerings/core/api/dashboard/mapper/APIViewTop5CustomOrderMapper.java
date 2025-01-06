package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTop5CustomOrderData;
import com.cplerings.core.api.dashboard.response.ViewTop5CustomOrderResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.dashboard.output.ViewTop5CustomOrderOutputData;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTop5CustomOrderMapper extends APINoRequestMapper<ViewTop5CustomOrderOutputData, ViewTop5CustomOrderData, ViewTop5CustomOrderResponse> {
}
