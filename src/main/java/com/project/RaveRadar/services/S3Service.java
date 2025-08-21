package com.project.RaveRadar.services;

import com.project.RaveRadar.utils.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Service
public class S3Service {

    @Value("${amazon.aws.bucket-name}")
    private String bucketName;

    @Value("${amazon.aws.region}")
    private String regionName;

    private final S3Presigner presigner;
    private final AuthUtil authUtil;

    public S3Service(S3Presigner presigner, AuthUtil authUtil) {
        this.presigner = presigner;
        this.authUtil = authUtil;
    }


    public URL generateProfilePictureUploadUrl(String contentType) {
        // fixed key, no file extension
        String key = "avatars/" + authUtil.getCurrentUser().getId();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(contentType) // store actual file type in metadata
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(15))
                .putObjectRequest(objectRequest)
                .build();

        return presigner.presignPutObject(presignRequest).url();
    }
}
