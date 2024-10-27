package com.cplerings.core.application.file.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.application.shared.service.storage.FileReturn;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface AFileUploadMapper {

    FileOutPut toOutput(FileReturn fileReturn);
}
