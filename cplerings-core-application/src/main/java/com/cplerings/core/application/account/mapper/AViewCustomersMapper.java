package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.account.datasource.result.Customers;
import com.cplerings.core.application.account.output.ViewCustomersOutput;
import com.cplerings.core.application.shared.mapper.AAccountMapper;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class,
        uses = {
                AAccountMapper.class
        })
public interface AViewCustomersMapper {

    @Mapping(target = "items", source = "customers")
    ViewCustomersOutput toOutput(Customers customers);
}
