package com.cplerings.core.infrastructure.aws;

import com.cplerings.core.common.profile.ProfileConstant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfiguration {

    @Value("${cplerings.aws.access-key}")
    private String accessKey;

    @Value("${cplerings.aws.secret-key}")
    private String secretKey;

    @Value("${cplerings.aws.region}")
    private String region;

    @Bean
    @Profile("!" + ProfileConstant.TEST)
    public S3Client s3Client() {
        final AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
