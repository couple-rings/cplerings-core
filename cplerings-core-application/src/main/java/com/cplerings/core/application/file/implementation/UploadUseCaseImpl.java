package com.cplerings.core.application.file.implementation;

import com.cplerings.core.application.file.UploadUseCase;
import com.cplerings.core.application.file.error.FileUploadErrorCode;
import com.cplerings.core.application.shared.service.storage.FileUploadInfo;
import com.cplerings.core.application.file.mapper.AFileUploadMapper;
import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.application.shared.service.storage.FileInfo;
import com.cplerings.core.application.shared.service.storage.FileStorageService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class UploadUseCaseImpl extends AbstractUseCase<FileUploadInfo, FileOutPut>
        implements UploadUseCase {

    private final FileStorageService fileStorageService;
    private final AFileUploadMapper aFileUploadMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, FileUploadInfo input) {
        validator.validateAndStopExecution(input != null, FileUploadErrorCode.FILE_INPUT_REQUIRED);
    }

    @Override
    protected FileOutPut internalExecute(UseCaseValidator validator, FileUploadInfo input) {
        FileInfo fileInfo = fileStorageService.uploadFile(input);
        validator.validateAndStopExecution(!fileInfo.hasError(), FileUploadErrorCode.FAULT_IN_UPLOADING_PHASE);
        return aFileUploadMapper.toOutput(fileInfo);
    }
}
