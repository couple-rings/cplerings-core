package com.cplerings.core.api.design.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.shared.mapper.APINoRequestMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIViewCustomDesignMapper  extends APINoRequestMapper<> {
}
