package com.cplerings.core.test.component.file;

import com.cplerings.core.common.profile.ProfileConstant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import java.net.URI;

@TestConfiguration
public class AWSTestConfiguration {

    public static final String AWS_S3_TEST_ENDPOINT_ENV = "cplerings.test.aws.s3.endpoint";

    @Value("${cplerings.test.aws.s3.endpoint}")
    private String awsS3TestEndpoint;

    @Value("${cplerings.aws.region}")
    private String region;

    @Value("${cplerings.aws.access-key}")
    private String accessKey;

    @Value("${cplerings.aws.secret-key}")
    private String secretKey;

    @Value("${cplerings.aws.s3.bucket-name}")
    private String bucketName;

    @Bean
    @Profile(ProfileConstant.TEST)
    public S3Client s3Client() {
        final AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        final S3Client s3Client = S3Client.builder()
                .endpointOverride(URI.create(awsS3TestEndpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
        s3Client.createBucket(CreateBucketRequest.builder()
                .bucket(bucketName)
                .build());
        return s3Client;
    }
}
