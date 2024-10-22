package com.cplerings.core.application.design.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cplerings.core.application.design.output.DesignCoupleInformation;
import com.cplerings.core.application.design.queryoutput.DesignCoupleQueryOutput;
import com.cplerings.core.application.design.queryoutput.DesignCouples;
import com.cplerings.core.application.design.output.ViewCoupleDesignOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AViewCoupleDesignMapper {

    @Mapping(target = "data", source = "designCouples")
    ViewCoupleDesignOutput toOutput(DesignCouples designs);

    @Mapping(target = "imageUrl", source = "previewImage.url")
    DesignCoupleInformation toCoupleDesignInformation(DesignCoupleQueryOutput designCoupleQueryOutput);
}
