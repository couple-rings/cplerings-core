package com.cplerings.core.test.integration.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cplerings.core.application.file.input.FileInput;
import com.cplerings.core.application.shared.service.storage.FileReturn;
import com.cplerings.core.infrastructure.service.storage.S3StorageServiceImpl;
import com.cplerings.core.test.shared.AbstractIT;

public class UploadFileUseCaseIT extends AbstractIT {

    @Mock
    private AmazonS3 s3Client;

    @InjectMocks
    private S3StorageServiceImpl s3StorageService;

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
    void givenBase64Image_whenUploadFileUseCaseIsExecuted_thenFileIsUploadedAndUrlIsReturned() throws Exception {
        // Arrange
        FileInput fileInput = new FileInput(sampleBase64Image);
        String expectedFileKey = "mocked-image-key.jpeg";

        String expectedFileUrl = "http://mock-s3-url/bucket-name/mocked-image.jpeg";
        URL mockUrl = new URL(expectedFileUrl);
        when(s3Client.putObject(Mockito.anyString(), Mockito.anyString(), Mockito.any(ByteArrayInputStream.class), Mockito.any(ObjectMetadata.class)))
                .thenReturn(null);

        when(s3Client.generatePresignedUrl(Mockito.any())).thenReturn(mockUrl);

        // Act
        FileReturn fileReturn = s3StorageService.uploadFile(fileInput);

        // Assert
        assertThat(fileReturn.hasError()).isFalse();
        assertThat(fileReturn.url()).isEqualTo(expectedFileUrl);
    }
}
