package com.cplerings.core.application.shared.service.storage;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.common.either.Either;

public interface FileStorageService {

    Either<FileInfo, ErrorCode> uploadFile(FileUploadInfo file);
}
