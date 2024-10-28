package com.cplerings.core.application.file;

import com.cplerings.core.application.shared.service.storage.FileUploadInfo;
import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface UploadUseCase extends UseCase<FileUploadInfo, FileOutPut> {
}
