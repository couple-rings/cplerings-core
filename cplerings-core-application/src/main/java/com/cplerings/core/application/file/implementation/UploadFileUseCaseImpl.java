package com.cplerings.core.application.file.implementation;

import com.cplerings.core.application.file.UploadFileUseCase;
import com.cplerings.core.application.file.error.FileErrorCode;
import com.cplerings.core.application.file.input.UploadFileInput;
import com.cplerings.core.application.file.mapper.AUploadFileMapper;
import com.cplerings.core.application.file.output.UploadFileOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.service.file.FileInfo;
import com.cplerings.core.application.shared.service.file.FileStorageService;
import com.cplerings.core.application.shared.service.file.FileUploadInfo;
import com.cplerings.core.application.shared.usecase.AbstractUseCase;
import com.cplerings.core.application.shared.usecase.UseCaseImplementation;
import com.cplerings.core.application.shared.usecase.UseCaseValidator;
import com.cplerings.core.common.either.Either;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@UseCaseImplementation
@RequiredArgsConstructor
public class UploadFileUseCaseImpl extends AbstractUseCase<UploadFileInput, UploadFileOutput>
        implements UploadFileUseCase {

    private final FileStorageService fileStorageService;
    private final AUploadFileMapper aUploadFileMapper;

    @Override
    protected void validateInput(UseCaseValidator validator, UploadFileInput input) {
        super.validateInput(validator, input);
        validator.validate(StringUtils.isNotBlank(input.fileBase64()), FileErrorCode.EMPTY_FILE);
    }

    @Override
    protected UploadFileOutput internalExecute(UseCaseValidator validator, UploadFileInput input) {
        final FileUploadInfo fileUploadInfo = aUploadFileMapper.toFileUploadInfo(input);
        fileUploadInfo.setType(FileUploadInfo.Type.DYNAMIC);
        final Either<FileInfo, ErrorCode> fileUploadResult = fileStorageService.uploadFile(fileUploadInfo);
        if (fileUploadResult.isRight()) {
            validator.validateAndStopExecution(false, fileUploadResult.getRight());
        }
        return aUploadFileMapper.toOutput(fileUploadResult.getLeft());
    }
}
