package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.ring.ARing;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.ring.Ring;

import org.mapstruct.Mapper;

@Mapper(
        config = SpringMapperConfiguration.class,
        uses = {
                AEnumMapper.class,
                ADocumentMapper.class,
                ASpouseMapper.class,
                ADesignMapper.class,
        }
)
public interface ARingMapper {

    ARing toRing(Ring ring);
}
