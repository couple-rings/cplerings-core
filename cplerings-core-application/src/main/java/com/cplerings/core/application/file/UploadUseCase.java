package com.cplerings.core.application.file;

import com.cplerings.core.application.file.input.FileInput;
import com.cplerings.core.application.file.output.FileOutPut;
import com.cplerings.core.application.shared.usecase.UseCase;

public interface UploadUseCase extends UseCase<FileInput, FileOutPut> {
}
