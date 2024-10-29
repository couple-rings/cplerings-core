package com.cplerings.core.test.shared;

import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.infrastructure.CplringsCoreApplication;
import com.cplerings.core.test.component.file.AWSTestConfiguration;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@SpringBootTest(
        classes = {
                CplringsCoreApplication.class
        }
)
@Import({
        CTConfiguration.class,
        AWSTestConfiguration.class
})
@ActiveProfiles(ProfileConstant.TEST)
@Testcontainers
public abstract class AbstractCT {

    @Container
    private static final LocalStackContainer AWS_CONTAINER = new LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"));

    private static final String BUCKET_NAME = UUID.randomUUID().toString();
    @Autowired
    private Flyway flyway;
    @Autowired
    private ObjectMapper objectMapper;

    @DynamicPropertySource
    static void configPropertiesForAWSS3(DynamicPropertyRegistry registry) {
        registry.add("cplerings.aws.region", AWS_CONTAINER::getRegion);
        registry.add("cplerings.aws.s3.bucket-name", () -> BUCKET_NAME);
        registry.add("cplerings.aws.s3.file-path-format", () -> String.format("https://%s.s3.%s.amazonaws.com/%%s", BUCKET_NAME, AWS_CONTAINER.getRegion()));
        registry.add("cplerings.aws.access-key", AWS_CONTAINER::getAccessKey);
        registry.add("cplerings.aws.secret-key", AWS_CONTAINER::getSecretKey);
        registry.add(AWSTestConfiguration.AWS_S3_TEST_ENDPOINT_ENV, () -> AWS_CONTAINER.getEndpointOverride(LocalStackContainer.Service.S3).toString());
    }

    @BeforeEach
    protected final void populateDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    protected final void thenResponseIsOk(WebTestClient.ResponseSpec response) {
        response.expectStatus().is2xxSuccessful();
    }

    protected final void thenResponseIsRedirection(WebTestClient.ResponseSpec response) {
        response.expectStatus().is3xxRedirection();
    }

    protected final TestDataLoader getTestDataLoader(String folder) {
        return TestDataLoader.builder()
                .folder(folder)
                .objectMapper(objectMapper)
                .build();
    }
}
