package com.cplerings.core.test.shared.spouse;

import com.cplerings.core.test.shared.AWSTestConfiguration;
import com.cplerings.core.test.shared.AbstractCT;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

@Testcontainers
public abstract class AbstractAWSCT extends AbstractCT {

    @Container
    private static final LocalStackContainer AWS_CONTAINER = new LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"));

    private static final String BUCKET_NAME = UUID.randomUUID().toString();

    @DynamicPropertySource
    static void configPropertiesForAWSS3(DynamicPropertyRegistry registry) {
        registry.add("cplerings.aws.region", AWS_CONTAINER::getRegion);
        registry.add("cplerings.aws.s3.bucket-name", () -> BUCKET_NAME);
        registry.add("cplerings.aws.s3.file-path-format", () -> String.format("https://%s.s3.%s.amazonaws.com/%%s", BUCKET_NAME, AWS_CONTAINER.getRegion()));
        registry.add("cplerings.aws.access-key", AWS_CONTAINER::getAccessKey);
        registry.add("cplerings.aws.secret-key", AWS_CONTAINER::getSecretKey);
        registry.add(AWSTestConfiguration.AWS_S3_TEST_ENDPOINT_ENV, () -> AWS_CONTAINER.getEndpointOverride(LocalStackContainer.Service.S3).toString());
    }
}
