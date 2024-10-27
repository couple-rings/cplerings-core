package com.cplerings.core.application.file.implementation;

import com.cplerings.core.application.file.UploadUseCase;
import com.cplerings.core.application.file.error.FileUploadErrorCode;
import com.cplerings.core.application.file.input.FileInput;
import com.cplerings.core.application.file.mapper.AFileUploadMapper;
import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.application.shared.service.storage.FileReturn;
import com.cplerings.core.application.shared.service.storage.S3StorageService;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;

import lombok.RequiredArgsConstructor;

@UseCaseImplementation
@RequiredArgsConstructor
public class UploadUseCaseImpl extends AbstractUseCase<FileInput, FileOutPut>
        implements UploadUseCase {

    private final S3StorageService s3StorageService;
    private final AFileUploadMapper aFileUploadMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, FileInput input) {
        validator.validateAndStopExecution(input != null, FileUploadErrorCode.FILE_INPUT_REQUIRED);
    }

    @Override
    protected FileOutPut internalExecute(UseCaseValidator validator, FileInput input) {
        FileReturn fileReturn = s3StorageService.uploadFile(input);
        validator.validateAndStopExecution(!fileReturn.hasError(), FileUploadErrorCode.FAULT_IN_UPLOADING_PHASE);
        return aFileUploadMapper.toOutput(fileReturn);
    }
}
