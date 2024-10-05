package com.cplerings.core.application.authentication.mapper;

import com.cplerings.core.application.authentication.output.AuthenticatedAccountOutput;
import com.cplerings.core.application.shared.mapper.ARoleMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ARoleMapper.class
        }
)
public interface AAuthenticateUserMapper {

    AuthenticatedAccountOutput toOutput(Account account);
}
