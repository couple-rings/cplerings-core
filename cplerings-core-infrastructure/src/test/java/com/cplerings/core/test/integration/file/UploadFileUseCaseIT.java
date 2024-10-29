package com.cplerings.core.test.integration.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.cplerings.core.application.shared.errorcode.ErrorCode;
import com.cplerings.core.application.shared.service.storage.FileInfo;
import com.cplerings.core.application.shared.service.storage.FileUploadInfo;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.infrastructure.service.storage.FileStorageServiceImpl;
import com.cplerings.core.test.shared.AbstractIT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UploadFileUseCaseIT extends AbstractIT {

    @Mock
    private S3Client s3Client;

    @InjectMocks
    private FileStorageServiceImpl s3StorageService;

    private String sampleBase64Image;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        byte[] sampleImageBytes = "sample image content".getBytes(StandardCharsets.UTF_8);
        sampleBase64Image = "data:image/jpeg;base64,/9j/" + Base64.getEncoder().encodeToString(sampleImageBytes);
//        if (s3StorageService instanceof S3StorageServiceImpl s3StorageServiceImpl) {
//            s3StorageServiceImpl.setS3Service(s3StorageService);
//        }
    }

    @Test
    void givenBase64Image_whenUploadFileUseCaseIsExecuted() throws Exception {
        // Arrange
        FileUploadInfo fileUploadInfo = new FileUploadInfo(sampleBase64Image);
        String expectedFileKey = "mocked-image-key.jpeg";

        String expectedFileUrl = "http://mock-s3-url/bucket-name/mocked-image.jpeg";
        URL mockUrl = new URL(expectedFileUrl);
        when(s3Client.putObject(Mockito.anyString(), Mockito.anyString(), Mockito.any(ByteArrayInputStream.class), Mockito.any(ObjectMetadata.class)))
                .thenReturn(null);

        when(s3Client.generatePresignedUrl(Mockito.any())).thenReturn(mockUrl);

        // Act
        Either<FileInfo, ErrorCode> fileUploadResult = s3StorageService.uploadFile(fileUploadInfo);

        // Assert
        thenFileIsUploadedAndURLIsReturned(fileUploadResult, expectedFileUrl);
    }

    private static void thenFileIsUploadedAndURLIsReturned(Either<FileInfo, ErrorCode> fileUploadResult, String expectedFileUrl) {
        assertThat(fileUploadResult.isLeft()).isTrue();
        assertThat(fileUploadResult.getLeft().url()).isEqualTo(expectedFileUrl);
    }
}
