package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.account.output.AccountProfileOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewProfileMapper {

    AccountProfileOutput toOutput(Account account);
}
