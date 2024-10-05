package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.ARole;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.account.Role;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface ARoleMapper {

    ARole toRole(Role role);
}
