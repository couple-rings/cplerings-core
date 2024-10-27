package com.cplerings.core.api.file.mapper;

import org.mapstruct.Mapper;

import com.cplerings.core.api.file.data.FileData;
import com.cplerings.core.api.file.request.FileUploadRequest;
import com.cplerings.core.api.file.response.FileUploadResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.file.input.FileInput;
import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIUploadFileMapper extends APIMapper<FileInput, FileOutPut, FileData, FileUploadRequest, FileUploadResponse> {

}
