package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                ARoleMapper.class
        }
)
public interface JWTGenerationMapper {

    @Mapping(target = "accountId", source = "id")
    JWTGenerationInput toInput(Account account);
}
