package com.cplerings.core.api.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.account.data.UsersData;
import com.cplerings.core.api.account.request.ViewUsersRequest;
import com.cplerings.core.api.account.response.ViewUsersResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.account.input.ViewUsersInput;
import com.cplerings.core.application.account.output.ViewUsersOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewUsersMapper extends APIMapper<ViewUsersInput, ViewUsersOutput, UsersData, ViewUsersRequest, ViewUsersResponse> {
}
