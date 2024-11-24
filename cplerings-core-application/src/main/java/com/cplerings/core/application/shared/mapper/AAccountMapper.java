package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.account.AAccount;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                ABranchMapper.class,
        }
)
public interface AAccountMapper {

    AAccount toAccount(Account account);
}
