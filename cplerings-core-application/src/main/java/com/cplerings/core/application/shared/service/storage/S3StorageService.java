package com.cplerings.core.application.shared.service.storage;

import com.cplerings.core.application.file.input.FileInput;

public interface S3StorageService {

    public FileReturn uploadFile(FileInput file);
}
