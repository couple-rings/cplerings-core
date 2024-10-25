package com.cplerings.core.application.shared.mapper;

import com.cplerings.core.application.shared.entity.design.AImage;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;
import com.cplerings.core.domain.file.Image;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AImageMapper {

    AImage toImage(Image image);
}
