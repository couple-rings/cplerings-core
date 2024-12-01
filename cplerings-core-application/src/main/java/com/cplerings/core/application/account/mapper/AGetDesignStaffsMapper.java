package com.cplerings.core.application.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.account.datasource.result.DesignStaffsResult;
import com.cplerings.core.application.account.output.GetDesignStaffsOutput;
import com.cplerings.core.application.account.output.result.DesignStaffsOutputResult;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AGetDesignStaffsMapper {

    @Mapping(target = "items", source = "staffs")
    GetDesignStaffsOutput toOutput(DesignStaffsOutputResult designStaffsOutputResult);

    DesignStaffsOutputResult toDesignStaff(DesignStaffsResult designStaffsResult);
}
