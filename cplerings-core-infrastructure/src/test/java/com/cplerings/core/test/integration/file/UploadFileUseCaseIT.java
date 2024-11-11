package com.cplerings.core.test.integration.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.cplerings.core.api.file.data.FileData;
import com.cplerings.core.api.file.request.FileUploadRequest;
import com.cplerings.core.api.file.response.FileUploadResponse;
import com.cplerings.core.api.shared.AbstractResponse;
import com.cplerings.core.application.shared.service.file.FileType;
import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.test.shared.AbstractIT;
import com.cplerings.core.test.shared.account.AccountTestConstant;
import com.cplerings.core.test.shared.helper.JWTTestHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.util.UUID;

class UploadFileUseCaseIT extends AbstractIT {

    private static final String FILE_FOLDER = "data/integration/file";
    private static final String VALID_IMAGE_FILE = "/valid-jpeg.json";

    @MockBean
    private S3Client s3Client;

    @Autowired
    private JWTTestHelper jwtTestHelper;

    @BeforeEach
    void setUpS3Client() {
        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
                .thenReturn(PutObjectResponse.builder()
                        .eTag(UUID.randomUUID().toString())
                        .build());
    }

    @Test
    void givenAuthenticated_whenUploadFile() {
        final FileUploadRequest request = getTestDataLoader(FILE_FOLDER).loadAsObject(VALID_IMAGE_FILE, FileUploadRequest.class);

        final String customerToken = jwtTestHelper.generateToken(AccountTestConstant.CUSTOMER_EMAIL);
        final WebTestClient.ResponseSpec response = requestBuilder()
                .path(APIConstant.FILES_PATH)
                .authorizationHeader(customerToken)
                .method(RequestBuilder.Method.POST)
                .body(request)
                .send();

        thenResponseIsOk(response);
        thenFileURLIsReturned(response);
    }

    private void thenFileURLIsReturned(WebTestClient.ResponseSpec response) {
        final FileUploadResponse responseBody = response.expectBody(FileUploadResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getType()).isEqualTo(AbstractResponse.Type.DATA);

        final FileData fileData = responseBody.getData();
        assertThat(fileData).isNotNull();
        assertThat(fileData.url()).isNotBlank();
        assertThat(fileData.id()).isNotNull();
        assertThat(fileData.type()).isEqualTo(FileType.IMAGE);
    }
}
