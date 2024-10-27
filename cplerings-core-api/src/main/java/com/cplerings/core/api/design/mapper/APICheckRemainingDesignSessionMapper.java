package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.data.RemainingDesignSessionData;
import com.cplerings.core.api.design.response.CheckRemainingDesignSessionResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.design.output.CheckRemainingDesignSessionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICheckRemainingDesignSessionMapper extends APINoRequestMapper<CheckRemainingDesignSessionOutput, RemainingDesignSessionData, CheckRemainingDesignSessionResponse> {

}
