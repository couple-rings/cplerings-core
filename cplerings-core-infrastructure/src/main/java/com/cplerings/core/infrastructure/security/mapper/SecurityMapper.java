package com.cplerings.core.infrastructure.security.mapper;

import com.cplerings.core.application.authentication.output.AuthenticatedAccountOutput;
import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface SecurityMapper {

    CurrentUser toCurrentUser(AuthenticatedAccountOutput output, boolean authenticated);
}
