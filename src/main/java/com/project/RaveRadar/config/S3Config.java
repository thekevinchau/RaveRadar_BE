package com.project.RaveRadar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

    @Value("${amazon.aws.accessKey}")
    private String accessKey;

    @Value("${amazon.aws.secret.accessKey}")
    private String secretKey;

    @Value("${amazon.aws.region}")
    private String regionName;

    @Bean
    public S3Client s3Client(){
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey,secretKey);
        return S3Client.builder()
                .region(Region.of(regionName))
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(accessKey,secretKey);
        return S3Presigner.builder()
                .region(Region.of(regionName))
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .build();
    }
}
