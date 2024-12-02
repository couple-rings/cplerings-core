package com.cplerings.core.api.design.mapper;

import com.cplerings.core.api.design.data.DesignSessionPaymentData;
import com.cplerings.core.api.design.response.CreateDesignSessionResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.design.output.CreateDesignSessionOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APICreateDesignSessionMapper extends APINoRequestMapper<CreateDesignSessionOutput, DesignSessionPaymentData, CreateDesignSessionResponse> {

}
