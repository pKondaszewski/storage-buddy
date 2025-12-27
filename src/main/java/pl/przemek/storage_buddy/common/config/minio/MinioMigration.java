package pl.przemek.storage_buddy.common.config.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class MinioMigration {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @EventListener(ApplicationReadyEvent.class)
    void migrate() throws Exception {
        ensureExistingBucket(minioProperties.bucket().name());
    }

    private void ensureExistingBucket(String bucketName) throws Exception {
        boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
}
