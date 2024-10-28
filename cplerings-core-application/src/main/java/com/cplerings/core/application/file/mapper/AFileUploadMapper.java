package com.cplerings.core.application.file.mapper;

import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.application.shared.service.storage.FileInfo;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface AFileUploadMapper {

    FileOutPut toOutput(FileInfo fileInfo);
}
