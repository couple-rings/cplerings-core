package com.cplerings.core.test.component.file;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.amazonaws.services.s3.AmazonS3;
import com.cplerings.core.application.file.input.FileInput;
import com.cplerings.core.application.shared.service.storage.FileReturn;
import com.cplerings.core.application.shared.service.storage.S3StorageService;
import com.cplerings.core.test.shared.AbstractCT;

@Testcontainers
public class S3StorageServiceCT extends AbstractCT {

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private S3StorageService s3StorageService;

    @Value("${application.bucket.name}")
    private String bucketName;

    private String sampleBase64Image;

    @Container
    private static final LocalStackContainer localstack = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.S3);

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
        FileInput fileInput = new FileInput(sampleBase64Image);
        thenFileIsUploadedAndUrlIsReturned(fileInput);
    }

    private void thenFileIsUploadedAndUrlIsReturned(FileInput fileInput) {
        // Act
        FileReturn fileReturn = s3StorageService.uploadFile(fileInput);

        // Assert
        assertThat(fileReturn.hasError()).isFalse();
        assertThat(fileReturn.url()).contains(bucketName);
    }
}
