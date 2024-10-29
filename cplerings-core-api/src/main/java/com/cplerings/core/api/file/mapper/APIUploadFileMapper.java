package com.cplerings.core.api.file.mapper;

import com.cplerings.core.api.file.data.FileData;
import com.cplerings.core.api.file.request.FileUploadRequest;
import com.cplerings.core.api.file.response.FileUploadResponse;
import com.cplerings.core.api.shared.mapper.APIMapper;
import com.cplerings.core.application.file.input.UploadFileInput;
import com.cplerings.core.application.file.output.UploadFileOutput;
import com.cplerings.core.common.mapper.SpringMapperConfiguration;

import org.mapstruct.Mapper;

@Mapper(config = SpringMapperConfiguration.class)
public interface APIUploadFileMapper extends APIMapper<UploadFileInput, UploadFileOutput, FileData, FileUploadRequest, FileUploadResponse> {

}
