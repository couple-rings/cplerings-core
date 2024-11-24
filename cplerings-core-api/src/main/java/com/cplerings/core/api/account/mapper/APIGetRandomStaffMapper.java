package com.cplerings.core.api.account.mapper;

import com.cplerings.core.api.account.response.GetRandomStaffResponse;
import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.application.account.output.GetRandomStaffOutput;
import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIGetRandomStaffMapper extends APINoRequestMapper<GetRandomStaffOutput, AAccount, GetRandomStaffResponse> {

    @Override
    @Mapping(target = ".", source = "staff")
    AAccount toData(GetRandomStaffOutput output);
}
