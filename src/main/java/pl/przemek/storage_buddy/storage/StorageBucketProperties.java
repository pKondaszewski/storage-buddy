package pl.przemek.storage_buddy.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "minio.bucket")
@Validated
record StorageBucketProperties(String name, Integer postExpiryMinutes) {}
