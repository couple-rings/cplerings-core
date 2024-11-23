package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.account.datasource.result.Users;
import com.cplerings.core.application.account.output.ViewUsersOutput;
import com.cplerings.core.application.shared.mapper.AAccountMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
            AAccountMapper.class
        }
)
public interface AViewUsersMapper {

    ViewUsersOutput toOutput(Users users);
}
