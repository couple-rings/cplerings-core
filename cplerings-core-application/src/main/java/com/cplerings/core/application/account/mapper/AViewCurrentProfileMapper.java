package com.cplerings.core.application.account.mapper;

import com.cplerings.core.application.account.output.ProfileOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewCurrentProfileMapper {

    ProfileOutput toOutput(Account account);
}
