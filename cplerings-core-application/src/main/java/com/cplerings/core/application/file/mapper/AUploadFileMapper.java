package com.cplerings.core.application.file.mapper;

import com.cplerings.core.application.file.input.UploadFileInput;
import com.cplerings.core.application.file.output.UploadFileOutput;
import com.cplerings.core.application.shared.service.file.FileInfo;
import com.cplerings.core.application.shared.service.file.FileUploadInfo;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SpringMapperConfiguration.class)
public interface AUploadFileMapper {

    @Mapping(target = "fileBase64", source = "fileBase64")
    FileUploadInfo toFileUploadInfo(UploadFileInput input);

    UploadFileOutput toOutput(FileInfo fileInfo);
}
