package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.design.DesignCharacteristic;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface ADesignCharacteristicMapper {

    ADesignCharacteristic toDesignCharacteristic(DesignCharacteristic designCharacteristic);
}
