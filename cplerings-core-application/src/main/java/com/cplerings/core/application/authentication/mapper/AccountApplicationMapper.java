package com.cplerings.core.application.authentication.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.authentication.output.AccountOutput;
import com.cplerings.core.application.authentication.output.RoleOutput;
import com.cplerings.core.common.mapper.DefaultMapperConfiguration;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;

@Mapper(config = DefaultMapperConfiguration.class)
public interface AccountApplicationMapper {

    AccountOutput toOutput(Account account);

    RoleOutput toRoleOutput(Role role);
}
