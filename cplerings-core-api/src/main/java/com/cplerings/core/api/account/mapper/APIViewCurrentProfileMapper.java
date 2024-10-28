package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.data.ProfileData;
import com.cplerings.core.api.account.response.ProfileResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.account.output.ProfileOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCurrentProfileMapper extends APINoRequestMapper<ProfileOutput, ProfileData, ProfileResponse> {


}
