package com.cplerings.core.application.verification.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.verification.output.ResendVerificationOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;

@Mapper(config = SpringMapperConfiguration.class)
public interface AResendVerificationMapper {

    ResendVerificationOutput toOutput(Account account);
}
