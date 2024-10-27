package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.data.AccountData;
import com.cplerings.core.api.account.request.ViewAccountRequest;
import com.cplerings.core.api.account.response.AccountResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.account.input.ViewAccountInput;
import com.cplerings.core.application.account.output.AccountOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewAccountMapper extends APIMapper<ViewAccountInput, AccountOutput, AccountData, ViewAccountRequest, AccountResponse> {

}
