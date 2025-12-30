package pl.przemek.storage_buddy.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "minio")
@Validated
record MinioProperties(String endpoint, String accessKey, String secretKey, BucketProperties bucket) {
    record BucketProperties(String name) {}
}
