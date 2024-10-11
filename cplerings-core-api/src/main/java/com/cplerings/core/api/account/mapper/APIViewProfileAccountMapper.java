package com.cplerings.core.api.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.account.data.AccountProfile;
import com.cplerings.core.api.account.request.ViewProfileAccountRequest;
import com.cplerings.core.api.account.response.AccountProfileResponse;
import com.cplerings.core.api.mapper.APIMapper;
import com.cplerings.core.application.account.input.ViewProfileInput;
import com.cplerings.core.application.account.output.AccountProfileOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewProfileAccountMapper extends APIMapper<ViewProfileInput, AccountProfileOutput, AccountProfile, ViewProfileAccountRequest, AccountProfileResponse> {
}
