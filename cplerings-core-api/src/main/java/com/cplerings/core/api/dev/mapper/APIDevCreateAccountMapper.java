package com.cplerings.core.api.dev.mapper;

import com.cplerings.core.api.dev.request.DevCreateAccountRequest;
import com.cplerings.core.api.dev.response.DevCreateAccountResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.account.input.DevCreateAccountInput;
import com.cplerings.core.application.account.output.DevCreateAccountOutput;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIDevCreateAccountMapper extends APIMapper<DevCreateAccountInput, DevCreateAccountOutput, AAccount, DevCreateAccountRequest, DevCreateAccountResponse> {

    @Override
    @Mapping(target = ".", source = "account")
    AAccount toData(DevCreateAccountOutput output);
}
