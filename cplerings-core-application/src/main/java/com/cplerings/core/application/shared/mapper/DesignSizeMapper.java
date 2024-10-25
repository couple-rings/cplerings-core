package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.shared.valueobject.DesignSize;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface DesignSizeMapper {

    default Integer toSize(DesignSize designSize) {
        if (designSize == null) {
            return 0;
        }
        return designSize.getSize();
    }

    default DesignSize toDesignSize(Integer size) {
        return DesignSize.create(size);
    }
}
