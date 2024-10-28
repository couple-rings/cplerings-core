package com.cplerings.core.test.component.file;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.application.shared.service.storage.FileUploadInfo;
import com.cplerings.core.application.shared.service.storage.FileInfo;
import com.cplerings.core.application.shared.service.storage.FileStorageService;
import com.cplerings.core.test.shared.AbstractCT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.amazonaws.services.s3.AmazonS3;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Testcontainers
public class FileStorageServiceCT extends AbstractCT {

    @Container
    private static final LocalStackContainer localstack = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.S3);
    @Autowired
    private AmazonS3 s3Client;
    @Autowired
    private FileStorageService fileStorageService;
    @Value("${application.bucket.name}")
    private String bucketName;
    private String sampleBase64Image;

    @BeforeEach
    void setUp() {
        s3Client.createBucket(bucketName);

        // Sample base64 string for an image file
        byte[] sampleImageBytes = "sample image content".getBytes(StandardCharsets.UTF_8);
        sampleBase64Image = "data:image/jpeg;base64,/9j/" + Base64.getEncoder().encodeToString(sampleImageBytes);
    }

    @Test
    void givenBase64Image_whenUploadFile_thenFileIsUploadedAndUrlIsReturned() {
        // Arrange
        FileUploadInfo fileUploadInfo = new FileUploadInfo(sampleBase64Image);
        thenFileIsUploadedAndUrlIsReturned(fileUploadInfo);
    }

    private void thenFileIsUploadedAndUrlIsReturned(FileUploadInfo fileUploadInfo) {
        // Act
        FileInfo fileInfo = fileStorageService.uploadFile(fileUploadInfo);

        // Assert
        assertThat(fileInfo.hasError()).isFalse();
        assertThat(fileInfo.url()).contains(bucketName);
    }
}
