package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalInForAllData;
import com.cplerings.core.api.dashboard.response.ViewTotalInForAllResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.dashboard.output.ViewTotalInForAllOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalInForAllMapper extends APINoRequestMapper<ViewTotalInForAllOutput, ViewTotalInForAllData, ViewTotalInForAllResponse> {
}
