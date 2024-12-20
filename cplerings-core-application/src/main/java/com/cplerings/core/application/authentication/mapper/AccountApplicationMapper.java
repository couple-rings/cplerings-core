package com.cplerings.core.application.authentication.mapper;

import com.cplerings.core.application.authentication.output.AccountOutput;
import com.cplerings.core.application.authentication.output.RoleOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AccountApplicationMapper {

    AccountOutput toOutput(Account account);

    RoleOutput toRoleOutput(Role role);
}
