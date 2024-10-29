package com.cplerings.core.test.component.file;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.service.file.FileInfo;
import com.cplerings.core.application.shared.service.file.FileStorageService;
import com.cplerings.core.application.shared.service.file.FileUploadInfo;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.test.shared.AbstractCT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class FileStorageServiceCT extends AbstractCT {

    private static final String FILE_FOLDER = "data/integration/file";
    private static final String VALID_IMAGE_FILE = "/valid-jpeg.json";

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    void givenFileStorageService_whenUploadFile() {
        final FileUploadInfo fileUploadInfo = getTestDataLoader(FILE_FOLDER).loadAsObject(VALID_IMAGE_FILE, FileUploadInfo.class);
        fileUploadInfo.setType(FileUploadInfo.Type.DYNAMIC);
        final Either<FileInfo, ErrorCode> fileUploadResult = fileStorageService.uploadFile(fileUploadInfo);

        thenFileUploadIsSuccessful(fileUploadResult);
        thenFileInfoContainsFileURL(fileUploadResult.getLeft());
    }

    private void thenFileUploadIsSuccessful(Either<FileInfo, ErrorCode> fileUploadResult) {
        assertThat(fileUploadResult.isLeft()).isTrue();
    }

    private void thenFileInfoContainsFileURL(FileInfo fileInfo) {
        assertThat(fileInfo).isNotNull();
        assertThat(fileInfo.url()).isNotBlank();
    }
}
