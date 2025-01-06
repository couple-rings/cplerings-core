package com.cplerings.core.api.dashboard.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.dashboard.data.ViewTotalExpenditureForAllData;
import com.cplerings.core.api.dashboard.response.ViewTotalExpenditureForAllResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.dashboard.output.ViewTotalExpenditureForAllOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewTotalExpenditureForAllMapper extends APINoRequestMapper<ViewTotalExpenditureForAllOutput, ViewTotalExpenditureForAllData, ViewTotalExpenditureForAllResponse> {
}
